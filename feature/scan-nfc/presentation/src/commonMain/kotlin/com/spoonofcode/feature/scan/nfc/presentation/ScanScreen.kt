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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.PlatformType
import com.spoonofcode.core.nfc.NfcReader
import com.spoonofcode.core.nfc.rememberNfcReader
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.LottieAnimation
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.SecondaryButton
import com.spoonofcode.core.presentation.compose.SpacerVerticalBetweenFields
import com.spoonofcode.core.presentation.compose.TextBL
import com.spoonofcode.core.presentation.compose.TextBLB
import com.spoonofcode.core.presentation.compose.TextBM
import com.spoonofcode.core.presentation.taggy_go_logo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.scan),
    backNavigationEnable = false,
    verticalScrollEnable = false,
)

@Composable
internal fun ScanScreen(
    viewModel: ScanViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: ScanViewState,
    onAction: (ScanViewAction) -> Unit,
) {
    if (viewState.isProductNotFoundViewVisible) {
        ProductNotFoundView(
            reload = { onAction(ScanViewAction.ScanAgain) }
        )
    } else {
        val reader: NfcReader = rememberNfcReader()
        val tag by reader.tags.collectAsState(initial = null)
        val isScanning by reader.isScanning.collectAsState()

        LaunchedEffect(Unit) { reader.start() }
        DisposableEffect(Unit) { onDispose { reader.stop() } }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isScanning) {
                val scanInfoText = when (viewState.platformType) {
                    PlatformType.Android -> stringResource(Res.string.scan_info_android)
                    PlatformType.iOS -> stringResource(Res.string.scan_info_ios)
                }
                TextBL(
                    text = scanInfoText,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )
                tag?.let {
                    onAction(ScanViewAction.CheckProduct(it))
                } ?: LottieAnimation(
                    animationFilePath = viewState.animationFilePath,
                    contentDescription = viewState.contentDescription,
                )

                TextBM(stringResource(Res.string.scanning_in_progress))
            } else {
                PrimaryButton(
                    text = stringResource(Res.string.start_scanning),
                    onClick = { reader.start() }
                )
            }

        }
    }
}

@Composable
private fun ProductNotFoundView(
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
        SpacerVerticalBetweenFields()

        TextBLB(stringResource(Res.string.product_not_found))

        SpacerVerticalBetweenFields()

        SecondaryButton(
            text = stringResource(resource = Res.string.scan_again),
            onClick = reload
        )
    }
}

// region previews
@Preview
@Composable
private fun ScanScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(ScanViewState()),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
