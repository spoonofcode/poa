package com.spoonofcode.feature.scan.nfc.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.PlatformType
import com.spoonofcode.core.nfc.NfcReader
import com.spoonofcode.core.nfc.rememberNfcReader
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.LottieAnimation
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.core.presentation.taggy_go_logo
import com.spoonofcode.feature.scan.nfc.presentation.product_not_found
import com.spoonofcode.feature.scan.nfc.presentation.scan_again
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ScanScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
    viewModel: ScanViewModel,
) : BaseScreen<ScanViewModel, ScanViewState, ScanViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.scan)

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
            if (viewState.isProductNotFoundViewVisible) {
                ProductNotFoundView(
                    reload = { onAction(ScanViewAction.ScanAgain) }
                )
            } else {
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
                        onAction(ScanViewAction.CheckProduct(it))
                    } ?: LottieAnimation(
                        animationFilePath = viewState.animationFilePath,
                        contentDescription = viewState.contentDescription,
                    )

                    Texts.BM(stringResource(Res.string.scanning_in_progress))
                }
            }
        }
    }

    @Composable
    fun ProductNotFoundView(
        iconRes: DrawableResource = com.spoonofcode.core.presentation.Res.drawable.taggy_go_logo,
        sizeDp: Dp = 160.dp,
        reload: () -> Unit = {},
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(Paddings.screenPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier
                    .size(160.dp),
                shape = CircleShape,
            ) {
                Image(
                    painter = painterResource(resource = iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(sizeDp)
                        .background(MaterialTheme.colorScheme.onBackground),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
                )
            }
            Spacers.VerticalBetweenFields()

            Texts.BLB(stringResource(Res.string.product_not_found))

            Spacers.VerticalBetweenFields()

            Buttons.SecondaryButton(
                text = stringResource(resource =Res.string.scan_again),
                onClick = reload
            )
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