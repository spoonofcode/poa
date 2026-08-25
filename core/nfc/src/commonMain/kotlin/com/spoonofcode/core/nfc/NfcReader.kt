package com.spoonofcode.core.nfc

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

data class NfcTag(
    val id: String,
    val techList: List<String>,
    val ndef: String? = null
)

interface NfcReader {
    /** Emits a tag every time the user taps one. */
    val tags: Flow<NfcTag>

    /** Whether the NFC reader is currently active. */
    val isScanning: StateFlow<Boolean>

    fun start()
    fun stop()
}

///** Factory to get the platform implementation. */
@Composable
expect fun rememberNfcReader(): NfcReader