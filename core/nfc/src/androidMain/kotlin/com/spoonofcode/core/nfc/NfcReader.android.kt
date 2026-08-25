package com.spoonofcode.core.nfc

import android.app.Activity
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.Ndef
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn

internal class AndroidNfcReader(private val activity: Activity) : NfcReader {

    private val _isScanning = MutableStateFlow(false)
    override val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    @OptIn(DelicateCoroutinesApi::class)
    private val _tags = callbackFlow {
        _isScanning.value = true
        val adapter = NfcAdapter.getDefaultAdapter(activity) ?: run {
            close()
            return@callbackFlow
        }

        val flags = NfcAdapter.FLAG_READER_NFC_A or
                NfcAdapter.FLAG_READER_NFC_B or
                NfcAdapter.FLAG_READER_NFC_F or
                NfcAdapter.FLAG_READER_NFC_V

        adapter.enableReaderMode(activity, { tag ->
            val id = tag.id.joinToString("") { "%02X".format(it) }
            val ndefPayload = readNdef(tag)

            trySend(NfcTag(id = id, techList = tag.techList.toList(), ndef = ndefPayload))
        }, flags, null)

        awaitClose {
            adapter.disableReaderMode(activity)
            _isScanning.value = false
        }
    }.shareIn(GlobalScope, SharingStarted.Eagerly, replay = 0)

    override val tags: Flow<NfcTag> = _tags
    override fun start() {}
    override fun stop() {}

    private fun readNdef(tag: Tag): String? {
        // 1. Try standard NDEF
        Ndef.get(tag)?.let { ndef ->
            return try {
                ndef.connect()
                val msg = ndef.ndefMessage
                ndef.close()
                parseNdefMessage(msg)
            } catch (e: Exception) {
                null
            }
        }

        // 2. Fallback for IsoDep (Type 4 tags like DESFire)
        IsoDep.get(tag)?.let { iso ->
            return try {
                iso.connect()
                // Select NDEF Application APDU
                val selectApp = byteArrayOf(
                    0.toByte(),
                    0xA4.toByte(),
                    4.toByte(),
                    0.toByte(),
                    7.toByte(),
                    0xD2.toByte(),
                    0x76.toByte(),
                    0.toByte(),
                    0.toByte(),
                    0x85.toByte(),
                    1.toByte(),
                    1.toByte(),
                    0.toByte()
                )
                val res = iso.transceive(selectApp)
                if (res.size >= 2 && res[res.size - 2] == 0x90.toByte()) {
                    iso.transceive(
                        byteArrayOf(
                            0,
                            0xA4.toByte(),
                            0,
                            0xC,
                            2,
                            0xE1.toByte(),
                            4.toByte()
                        )
                    ) // Select NDEF File
                    val readLen =
                        iso.transceive(byteArrayOf(0, 0xB0.toByte(), 0, 0, 2)) // Read length
                    val len = ((readLen[0].toInt() and 0xFF) shl 8) or (readLen[1].toInt() and 0xFF)
                    val data = iso.transceive(
                        byteArrayOf(
                            0,
                            0xB0.toByte(),
                            0,
                            2,
                            len.toByte()
                        )
                    ) // Read actual data
                    parseNdefMessage(NdefMessage(data.copyOfRange(0, len)))
                } else null
            } catch (e: Exception) {
                null
            } finally {
                iso.close()
            }
        }
        return null
    }

    private fun parseNdefMessage(message: NdefMessage?): String? {
        val record = message?.records?.firstOrNull() ?: return null
        return try {
            record.toUri()?.toString() ?: record.payload.decodeToString()
        } catch (e: Exception) {
            record.payload.decodeToString()
        }
    }
}

@Composable
actual fun rememberNfcReader(): NfcReader = AndroidNfcReader(LocalActivity.current as Activity)
