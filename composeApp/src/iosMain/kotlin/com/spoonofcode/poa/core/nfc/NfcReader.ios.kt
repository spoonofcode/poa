package com.spoonofcode.poa.core.nfc

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal class IosNfcReader : NfcReader {
    override val tags: Flow<NfcTag> = emptyFlow() // Plug in NFCTagReaderSession here
    override fun start()  { /* TODO */ }
    override fun stop()   { /* TODO */ }
}

@Composable
actual fun rememberNfcReader(): NfcReader = IosNfcReader()