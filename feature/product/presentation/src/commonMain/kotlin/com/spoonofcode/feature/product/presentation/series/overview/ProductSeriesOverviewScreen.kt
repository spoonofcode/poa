package com.spoonofcode.feature.product.presentation.series.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.partner.domain.model.ProductSeries
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.my_product_series
import com.spoonofcode.feature.product.presentation.send_notification
import com.spoonofcode.feature.product.presentation.taggy_go_logo
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.my_product_series),
    verticalScrollEnable = false,
)

@Composable
internal fun ProductSeriesOverviewScreen(
    viewModel: ProductSeriesOverviewViewModel,
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
    viewState: ProductSeriesOverviewViewState,
    onAction: (ProductSeriesOverViewViewAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
    ) {
        items(viewState.productSeries) { productSerie ->
            val checkedProductSeries =
                viewState.checkedProductSeriesNames.contains(productSerie.name)

            ProductSeriesItem(
                item = productSerie,
                onClick = {
                    onAction(
                        ProductSeriesOverViewViewAction.CheckedProductSeries(
                            productSerie.name
                        )
                    )
                },
                checkedProductSeries = checkedProductSeries,
            )
        }
    }

    Buttons.PrimaryButton(
        enabled = viewState.checkedProductSeriesNames.isNotEmpty(),
        text = stringResource(resource = Res.string.send_notification),
        onClick = { onAction(ProductSeriesOverViewViewAction.NavigateToNotification) }
    )
}

@Composable
private fun ProductSeriesItem(
    item: ProductSeries,
    onClick: () -> Unit,
    checkedProductSeries: Boolean,
) {
    Cards.ElevatedCard(
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Images.CircleImage(
                imageRes = Res.drawable.taggy_go_logo,
                imageSize = 64.dp,
            )
            Spacers.HorizontalBetweenFields()

            Texts.BLB(item.name)

            Spacers.Weight1(this)

            Checkbox(
                checked = checkedProductSeries,
                onCheckedChange = { onClick() }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun ProductSeriesOverviewScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            ProductSeriesOverviewViewState(
                productSeries = listOf(
                    ProductSeries(
                        askContactMessage = "Skontaktuj się z nami, aby dowiedzieć się więcej o tej serii.",
                        banner = "https://example.com/assets/banner-cyberpunk.jpg",
                        bannerIsOn = true,
                        blockchain = "Ethereum",
                        contactAddress = "0x71C7656EC7ab88b098defB751B7401B5f6d8976F",
                        description = "Limitowana seria cyfrowych kart kolekcjonerskich inspirowana motywami cyberpunkowymi.",
                        enabled = true,
                        facebook = "https://facebook.com/CyberPunkSeries",
                        fullName = "Cyberpunk Genesis Collectibles Series 2026",
                        instagram = "https://instagram.com/cyberpunk_series",
                        message = "Dziękujemy za zainteresowanie naszą serią produktów!",
                        name = "Cyberpunk Genesis",
                        owner = "0x324123456789abcdef0123456789abcdef012345",
                        ownerEmail = "kontakt@cyberpunkseries.io",
                        partnerLink = "https://partner-store.com/cyberpunk",
                        partnerLinkText = "Kup u oficjalnego partnera",
                        tiktok = "https://tiktok.com/@cyberpunk_series",
                        x = "https://x.com/cyberpunk_series",
                        youtube = "https://youtube.com/@CyberpunkSeriesOfficial"
                    )
                )
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
