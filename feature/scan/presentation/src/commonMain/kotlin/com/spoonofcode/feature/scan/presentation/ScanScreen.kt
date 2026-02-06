package com.spoonofcode.feature.scan.presentation

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.data.PlatformType
import com.spoonofcode.core.nfc.NfcReader
import com.spoonofcode.core.nfc.rememberNfcReader
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.LottieAnimation
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource

internal class ScanScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ScanViewModel, ScanViewState, ScanViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.scan

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
                val scanInfoText = when (viewState.platformType) {
                    PlatformType.Android -> stringResource(Res.string.scan_info_android)
                    PlatformType.iOS -> stringResource(Res.string.scan_info_ios)
                }
                Texts.BL(
                    text = scanInfoText,
                    textAlign = TextAlign.Center,
                )
                tag?.let {
                    onAction(ScanViewAction.CheckProduct(it.id))
                } ?: LottieAnimation(
                    animationFilePath = viewState.animationFilePath,
                    contentDescription = viewState.contentDescription,
                )

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