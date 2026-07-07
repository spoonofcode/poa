package com.spoonofcode.core.nfc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.*
import kotlinx.cinterop.get
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import platform.CoreNFC.*
import platform.Foundation.*
import platform.darwin.NSObject

/* ---------- small platform helpers ---------- */

private fun ByteArray.toHex(): String =
    joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0').uppercase() }

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray = memScoped {
    val size = length.toInt()
    val buf = bytes?.reinterpret<ByteVar>() ?: error("No bytes")
    ByteArray(size) { idx -> buf[idx] }
}

private fun NSData?.utf8(): String? =
    this?.let { NSString.create(it, NSUTF8StringEncoding) as String }

/* ---------- KOTLIN-ONLY reader that UI will use ---------- */

internal class IosNfcReader : NfcReader {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _tags = MutableSharedFlow<NfcTag>(extraBufferCapacity = 1)
    override val tags: Flow<NfcTag> = _tags.asSharedFlow()

    private var session: NFCTagReaderSession? = null

    private val delegate = object : NSObject(), NFCTagReaderSessionDelegateProtocol {

        override fun tagReaderSessionDidBecomeActive(session: NFCTagReaderSession) {
            println("NFC: Session active")
        }

        override fun tagReaderSession(session: NFCTagReaderSession, didInvalidateWithError: NSError) {
            println("NFC: Session invalidated: ${didInvalidateWithError.localizedDescription}")
            this@IosNfcReader.session = null
        }

        override fun tagReaderSession(session: NFCTagReaderSession, didDetectTags: List<*>) {
            val tag = (didDetectTags.firstOrNull() as? NFCTagProtocol) ?: return

            session.connectToTag(tag) { err ->
                if (err != null) {
                    println("NFC: Connect error: ${err.localizedDescription}")
                    session.invalidateSessionWithErrorMessage(err.localizedDescription)
                    return@connectToTag
                }

                scope.launch {
                    try {
                        val mifare = tag.asNFCMiFareTag()
                        val iso7816 = tag.asNFCISO7816Tag()
                        val iso15693 = tag.asNFCISO15693Tag()
                        val felica = tag.asNFCFeliCaTag()

                        val techList = mutableListOf<String>()
                        var uid = ""

                        when {
                            mifare != null -> {
                                uid = mifare.identifier.toByteArray().toHex()
                                techList.add("MIFARE")
                            }
                            iso7816 != null -> {
                                uid = iso7816.identifier.toByteArray().toHex()
                                techList.add("ISO7816")
                            }
                            iso15693 != null -> {
                                uid = iso15693.identifier.toByteArray().toHex()
                                techList.add("ISO15693")
                            }
                            felica != null -> {
                                uid = felica.currentIDm.toByteArray().toHex()
                                techList.add("FeliCa")
                            }
                        }
                        if (techList.isEmpty()) techList.add("Unknown")

                        println("NFC: Tag detected: $techList, ID: $uid")

                        var ndef: String? = null
                        val ndefTag = (tag as? NFCNDEFTagProtocol)
                            ?: (mifare as? NFCNDEFTagProtocol)
                            ?: (iso7816 as? NFCNDEFTagProtocol)
                            ?: (iso15693 as? NFCNDEFTagProtocol)
                            ?: (felica as? NFCNDEFTagProtocol)

                        if (ndefTag != null) {
                            suspendCancellableCoroutine<Unit> { cont ->
                                ndefTag.readNDEFWithCompletionHandler { msg, error ->
                                    if (error != null) {
                                        println("NFC: NDEF read error: ${error.localizedDescription}")
                                    } else {
                                        val record = msg?.records?.firstOrNull() as? NFCNDEFPayload
                                        val payload = record?.payload
                                        ndef = payload.utf8()
                                        println("NFC: NDEF read success: $ndef")
                                    }
                                    cont.resume(Unit) {}
                                }
                            }
                        }

                        _tags.emit(NfcTag(id = uid, techList = techList, ndef = ndef))
                        session.invalidateSession()
                    } catch (e: Exception) {
                        println("NFC: Extraction failed: ${e.message}")
                        session.invalidateSessionWithErrorMessage("Failed to read tag data")
                    }
                }
            }
        }
    }

    /* ------------- NfcReader interface ------------- */
    override fun start() {
        if (session != null) return        // already active
        session = NFCTagReaderSession(
            pollingOption = NFCPollingISO14443 or
                    NFCPollingISO15693 or
                    NFCPollingISO18092,
            delegate = delegate,
            queue = null           // main thread
        ).apply {
            alertMessage = "Hold your iPhone near the NFC tag."
            beginSession()
        }
    }

    override fun stop() {
        session?.invalidateSession()
        session = null
    }
}

/* ---------- Compose helper ---------- */

@Composable
actual fun rememberNfcReader(): NfcReader = remember { IosNfcReader() }