package com.spoonofcode.poa.core.nfc

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreNFC.NFCFeliCaTagProtocol
import platform.CoreNFC.NFCISO15693TagProtocol
import platform.CoreNFC.NFCISO7816TagProtocol
import platform.CoreNFC.NFCMiFareTagProtocol
import platform.CoreNFC.NFCNDEFPayload
import platform.CoreNFC.NFCNDEFTagProtocol
import platform.CoreNFC.NFCPollingISO14443
import platform.CoreNFC.NFCPollingISO15693
import platform.CoreNFC.NFCPollingISO18092
import platform.CoreNFC.NFCTagProtocol
import platform.CoreNFC.NFCTagReaderSession
import platform.CoreNFC.NFCTagReaderSessionDelegateProtocol
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.darwin.NSObject

/** Hex-encode a ByteArray the Android way */
private fun ByteArray.toHex(): String =      // ← new helper
    joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0').uppercase() }

/** Convert Obj-C NSData to Kotlin ByteArray */
@ExperimentalForeignApi
private fun NSData.toByteArray(): ByteArray = memScoped {
    val size = length.toInt()
    val buffer = bytes?.reinterpret<ByteVar>() ?: error("No bytes")
    ByteArray(size) { index -> buffer[index] }
}

/** Convert NSData (UTF-8) to Kotlin String, null-safe */
private fun NSData?.utf8(): String? =
    this?.let { NSString.create(it, NSUTF8StringEncoding) as String }

@OptIn(ExperimentalForeignApi::class)
internal class IosNfcReader : NSObject(),
    NFCTagReaderSessionDelegateProtocol,        // the Obj-C protocol
    NfcReader                                         // our common interface
{
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _tags = MutableSharedFlow<NfcTag>(extraBufferCapacity = 1)
    override val tags: Flow<NfcTag> = _tags.asSharedFlow()

    private var session: NFCTagReaderSession? = null

    // -------- NfcReader API --------
    override fun start() {
        if (session != null) return          // already running

        session = NFCTagReaderSession(
            pollingOption = NFCPollingISO14443 or
                    NFCPollingISO15693 or
                    NFCPollingISO18092,
            delegate = this,
            queue = null              // callbacks on main thread
        ).apply {
            alertMessage = "Hold your iPhone near the NFC tag."
            beginSession()
        }
    }

    override fun stop() {
        session?.invalidateSession()
        session = null
    }

    // -------- NFCTagReaderSessionDelegate --------
    /** iOS calls this once the reader is ready */
    override fun tagReaderSessionDidBecomeActive(session: NFCTagReaderSession) = Unit

    /** Called when the session ends (timeout, cancel, error) */
    override fun tagReaderSession(
        session: NFCTagReaderSession,
        didInvalidateWithError: NSError
    ) {
        this.session = null                  // allow restart
    }

    /**
     * *The important callback* – receives every discovered tag.
     * Signature matches Kotlin/Native’s mapping of
     * `tagReaderSession(_:, didDetect:)` in Swift :contentReference[oaicite:1]{index=1}
     */
    @Suppress("UNCHECKED_CAST")   // generic erasure from Obj-C → Kotlin
    override fun tagReaderSession(
        session: NFCTagReaderSession,
        didDetectTags: List<*>
    ) {
        val first = didDetectTags.firstOrNull() as? NFCTagProtocol ?: return

        // Step 1 – connect to the tag
        session.connectToTag(first) { connectError ->
            if (connectError != null) {
                session.invalidateSessionWithErrorMessage(
                    connectError.localizedDescription ?: "NFC error"
                )
                return@connectToTag
            }

            // Step 2 – pull a UID and optional NDEF message
            scope.launch {
                val (uid, tech) = when (first) {
                    is NFCMiFareTagProtocol -> first.identifier.toByteArray().toHex() to "MIFARE"
                    is NFCISO15693TagProtocol -> first.identifier.toByteArray()
                        .toHex() to "ISO15693"

                    is NFCISO7816TagProtocol -> first.identifier.toByteArray()
                        .toHex() to "ISO7816"

                    is NFCFeliCaTagProtocol -> first.currentIDm.toByteArray().toHex() to "FeliCa"
                    else -> "" to "Unknown"
                }

                // If it’s NDEF-capable ask for the first record (optional)
                var ndef: String? = null
                (first as? NFCNDEFTagProtocol)?.let { ndefTag ->
                    suspendCancellableCoroutine<Unit> { cont ->
                        ndefTag.readNDEFWithCompletionHandler { msg, err ->
                            ndef = (msg?.records?.firstOrNull() as? NFCNDEFPayload)?.payload.utf8()
                            cont.resume(Unit) {}
                        }
                    }
                }

                _tags.emit(NfcTag(id = uid, techList = listOf(tech), ndef = ndef))
                session.invalidateSession()                  // auto-restart feels nicer
            }
        }
    }
}

@Composable
actual fun rememberNfcReader(): NfcReader = IosNfcReader()