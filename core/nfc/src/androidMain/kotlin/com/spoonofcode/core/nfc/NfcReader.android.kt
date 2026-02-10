package com.spoonofcode.core.nfc

import android.app.Activity
import android.nfc.NfcAdapter
import android.nfc.tech.Ndef
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn

internal class AndroidNfcReader(private val activity: Activity) : NfcReader {

    @OptIn(DelicateCoroutinesApi::class)
    private val _tags = callbackFlow {
        val adapter = NfcAdapter.getDefaultAdapter(activity) ?: run {
            // no NFC hardware
            close()
            return@callbackFlow
        }
        val flags = NfcAdapter.FLAG_READER_NFC_A or
                NfcAdapter.FLAG_READER_NFC_B or
                NfcAdapter.FLAG_READER_NFC_F or
                NfcAdapter.FLAG_READER_NFC_V or
                NfcAdapter.FLAG_READER_NFC_BARCODE
        val readerCallback = NfcAdapter.ReaderCallback { tag ->
            val id = tag.id.joinToString("") { "%02X".format(it) }
            val ndefPayload = Ndef.get(tag)?.cachedNdefMessage
                ?.records?.firstOrNull()
                ?.payload
                ?.decodeToString()
            trySend(
                NfcTag(
                    id = id,
                    techList = tag.techList.toList(),
                    ndef = ndefPayload
                )
            )
        }
        adapter.enableReaderMode(activity, readerCallback, flags, null)
        awaitClose { adapter.disableReaderMode(activity) }
    }.shareIn(GlobalScope, SharingStarted.Eagerly, replay = 0)

    override val tags: Flow<NfcTag> = _tags
    override fun start() { /* reader mode already enabled in flow */
    }

    override fun stop() { /* flow’s awaitClose disables it */
    }
}

@Composable
actual fun rememberNfcReader(): NfcReader = AndroidNfcReader(LocalActivity.current as Activity)