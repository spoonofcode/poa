package com.spoonofcode.poa.feature.product.presentation.series.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.spoonofcode.poa.feature.product.data.testdata.PRODUCTS
import com.spoonofcode.poa.feature.product.domain.model.Product
import poa.core.presentation.generated.resources.Res
import poa.core.presentation.generated.resources.my_product_series
import poa.core.presentation.generated.resources.send_notification
import poa.core.presentation.generated.resources.series_number
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
                    items(viewState.productSeries) { product ->
                        val checkedProductSeries =
                            viewState.checkedProductSeriesIds.contains(product.seriesId)

                        ProductSeriesItem(
                            item = product,
                            onClick = {
                                onAction(
                                    ProductSeriesOverViewViewAction.CheckedProductSeries(
                                        product.seriesId
                                    )
                                )
                            },
                            checkedProductSeries = checkedProductSeries,
                        )
                    }
                }

                Buttons.PrimaryButton(
                    enabled = viewState.checkedProductSeriesIds.isNotEmpty(),
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
        item: Product,
        onClick: () -> Unit,
        checkedProductSeries: Boolean,
    ) {
        Cards.ElevatedCard(
            onClick = onClick
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Images.ImageLink(
                    imageLink = item.imageLink,
                    modifier = Modifier.size(80.dp),
                    )

                Spacers.HorizontalBetweenFields()

                Texts.BLB(
                    text = stringResource(
                        resource = Res.string.series_number,
                        item.seriesId
                    )
                )

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
                productSeries = PRODUCTS,
            )
        )
}
// endregion