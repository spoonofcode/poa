package com.spoonofcode.feature.scan.presentation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.ext.koinViewModel

internal class ScanScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ScanViewModel, ScanViewState, ScanViewAction>() {

//    override fun provideTopAppBarTitle() = Res.string.scan

    @Composable
    override fun provideViewModel() = koinViewModel<ScanViewModel>()

    @Composable
    override fun provideContent(
        viewState: ScanViewState,
        onAction: (ScanViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            // TODO #109 Fix after migration to feature modules
//            val reader: NfcReader = rememberNfcReader()
//            val tag by reader.tags.collectAsState(initial = null)
//
//            LaunchedEffect(Unit) { reader.start() }
//            DisposableEffect(Unit) { onDispose { reader.stop() } }

            // TODO #109 Fix after migration to feature modules
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                val scanInfoText = when (viewState.platformType) {
//                    PlatformType.Android -> stringResource(Res.string.scan_info_android)
//                    PlatformType.iOS -> stringResource(Res.string.scan_info_ios)
//                }
//                Texts.BL(
//                    text = scanInfoText,
//                    textAlign = TextAlign.Center,
//                )
//
//                // TODO #109 Fix after migration to feature modules
////                tag?.let {
////                    onAction(ScanViewAction.CheckProduct(it.id))
////                } ?: LottieAnimation(
////                    animationFilePath = viewState.animationFilePath,
////                    contentDescription = viewState.contentDescription,
////                )
//
//                Texts.BM(stringResource(Res.string.scanning_in_progress))
//            }
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