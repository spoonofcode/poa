package com.spoonofcode.poa.feature.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.spoonofcode.poa.core.nfc.NfcReader
import com.spoonofcode.poa.core.nfc.rememberNfcReader
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.appsettings.AppSettingsScreen
import com.spoonofcode.poa.feature.appsettings.AppSettingsViewState
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.demo
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class DemoScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<DemoViewModel, DemoViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<DemoViewModel>()

    @Composable
    override fun provideContent(
        viewModel: DemoViewModel?,
        viewState: DemoViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel?.initView()
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: DemoViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BL(text = stringResource(resource = Res.string.demo))

            NfcScreen()
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
                println("ID  : ${it.id}")
                Texts.BLB("Tech: ${it.techList.joinToString()}")
                it.ndef?.let { ndef ->
                    Texts.BLB("NDEF: $ndef")
                    println("$ndef")
                }
            } ?: Texts.BLB("Waiting…")
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        ContentView(
            viewState = DemoViewState(),
        )
    }
}

// region previews
@Preview
@Composable
private fun DemoScreenContentPreview() {
    DemoScreen().PreviewContent(
        DemoViewState()
    )
}
// endregion