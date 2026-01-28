package com.spoonofcode.core.data.nfc

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

data class NfcTag(
    val id: String,
    val techList: List<String>,
    val ndef: String? = null
)

interface NfcReader {
    /** Emits a tag every time the user taps one. */
    val tags: Flow<NfcTag>
    fun start()
    fun stop()
}

// TODO #109 Fix after migration to feature modules
///** Factory to get the platform implementation. */
//@Composable
//expect fun rememberNfcReader(): NfcReader