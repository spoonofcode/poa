package com.spoonofcode.poa.feature.scan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.spoonofcode.poa.core.nfc.NfcReader
import com.spoonofcode.poa.core.nfc.rememberNfcReader
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.scan
import org.jetbrains.compose.resources.StringResource

internal class ScanScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.scan,
    override val backNavigationEnable: Boolean = false,
    override val contentPadding: PaddingValues = PaddingValues(
        start = Paddings.screenPadding,
        top = Paddings.screenPadding,
        bottom = Paddings.screenPadding,
    )
) : BaseScreen<ScanViewModel, ScanViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ScanViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ScanViewModel,
        viewState: ScanViewState
    ): @Composable ColumnScope.() -> Unit {

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    private fun ContentView(
        viewState: ScanViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB("HOME")

            NfcScreen()

            // TODO Add Plus floating button to add nft (scan)
        }
    }

    @Composable
    fun NfcScreen(reader: NfcReader = rememberNfcReader()) {
        val tag by reader.tags.collectAsState(initial = null)

        LaunchedEffect(Unit) { reader.start() }
        DisposableEffect(Unit) { onDispose { reader.stop() } }

        Column {
            Texts.BLB("Tap an NFC tag")
            tag?.let {
                Texts.BLB("ID  : ${it.id}")
                Texts.BLB("Tech: ${it.techList.joinToString()}")
                Texts.BLB("ID  : ${it.id}")
                it.ndef?.let { ndef ->
                    Texts.BLB("NDEF: $ndef")
                }
            } ?: Texts.BLB("Waiting…")
        }
    }


    // region previews
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedScanScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = ScanViewState(),
            )
        )
    }
    // endregion
}