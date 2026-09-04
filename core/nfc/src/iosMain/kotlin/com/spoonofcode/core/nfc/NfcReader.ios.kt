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

    private val _isScanning = MutableStateFlow(false)
    override val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    private var session: NFCNDEFReaderSession? = null

    private val delegate = object : NSObject(), NFCNDEFReaderSessionDelegateProtocol {

        override fun readerSessionDidBecomeActive(session: NFCNDEFReaderSession) {
            println("NFC: NDEF Session active")
            _isScanning.value = true
        }

        override fun readerSession(session: NFCNDEFReaderSession, didInvalidateWithError: NSError) {
            println("NFC: NDEF Session invalidated: ${didInvalidateWithError.localizedDescription}")
            this@IosNfcReader.session = null
            _isScanning.value = false
        }

        override fun readerSession(session: NFCNDEFReaderSession, didDetectNDEFs: List<*>) {
            val message = didDetectNDEFs.firstOrNull() as? NFCNDEFMessage ?: return
            val record = message.records.firstOrNull() as? NFCNDEFPayload
            val payload = record?.payload
            val ndef = payload.utf8()

            println("NFC: NDEF read success: $ndef")

            scope.launch {
                _tags.emit(NfcTag(id = "NDEF-ONLY", techList = listOf("NDEF"), ndef = ndef))
            }
        }
    }

    /* ------------- NfcReader interface ------------- */
    override fun start() {
        if (session != null) return        // already active
        session = NFCNDEFReaderSession(
            delegate = delegate,
            queue = null,          // main thread
            invalidateAfterFirstRead = false
        ).apply {
            alertMessage = "Hold your iPhone near the NFC tag."
            beginSession()
        }
    }

    override fun stop() {
        session?.invalidateSession()
        session = null
        _isScanning.value = false
    }
}

/* ---------- Compose helper ---------- */

@Composable
actual fun rememberNfcReader(): NfcReader = remember { IosNfcReader() }