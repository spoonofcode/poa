package com.spoonofcode.poa.feature.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.nfc.NfcReader
import com.spoonofcode.poa.core.nfc.rememberNfcReader
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.LottieAnimation
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.scan
import com.spoonofcode.poa.resources.scan_info
import com.spoonofcode.poa.resources.scanning_in_progress
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ScanScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ScanViewModel, ScanViewState, ScanViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.scan

    override fun provideInitAction(onAction: (ScanViewAction) -> Unit): () -> Unit =
        { onAction(ScanViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<ScanViewModel>()

    @Composable
    override fun provideContent(
        viewState: ScanViewState,
        onAction: (ScanViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val reader: NfcReader = rememberNfcReader()
            val tag by reader.tags.collectAsState(initial = null)

            LaunchedEffect(Unit) { reader.start() }
            DisposableEffect(Unit) { onDispose { reader.stop() } }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                tag?.let {
                    onAction(ScanViewAction.CheckProduct(it.id))
                } ?: LottieAnimation(
                    animationFilePath = viewState.animationFilePath,
                    contentDescription = viewState.contentDescription,
                )

                Texts.BL(stringResource(Res.string.scan_info))
                Texts.BM(stringResource(Res.string.scanning_in_progress))
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun ScanScreenContentPreview() {
    ScanScreen().PreviewContent(
        ScanViewState()
    )
}
// endregion