package com.spoonofcode.feature.product.presentation.series.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.partner.domain.model.ProductSeries
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.my_product_series
import com.spoonofcode.feature.product.presentation.send_notification
import com.spoonofcode.feature.product.presentation.taggy_go_logo
import org.jetbrains.compose.resources.stringResource

internal class ProductSeriesOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ProductSeriesOverviewViewModel, ProductSeriesOverviewViewState, ProductSeriesOverViewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.my_product_series

    override fun provideInitAction(onAction: (ProductSeriesOverViewViewAction) -> Unit): () -> Unit =
        { onAction(ProductSeriesOverViewViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductSeriesOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewState: ProductSeriesOverviewViewState,
        onAction: (ProductSeriesOverViewViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp),
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
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(resource = Res.string.send_notification),
                    onClick = { onAction(ProductSeriesOverViewViewAction.NavigateToNotification) }
                )
            }
        }
    }

    @Composable
    fun ProductSeriesItem(
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
}

// region previews
@Preview
@Composable
fun ProductSeriesOverviewScreenContentPreview() {
    ProductSeriesOverviewScreen()
        .PreviewContent(
            viewState = ProductSeriesOverviewViewState(
//                productSeries = PRODUCTS,
            )
        )
}
// endregion