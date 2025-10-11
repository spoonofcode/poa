package com.spoonofcode.poa.feature.product.series.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_product_series
import com.spoonofcode.poa.resources.send_notification
import com.spoonofcode.poa.resources.series_number
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProductSeriesOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ProductSeriesOverviewViewModel, ProductSeriesOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.my_product_series

    override fun provideInitAction(viewModel: ProductSeriesOverviewViewModel?): () -> Unit =
        { viewModel?.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductSeriesOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProductSeriesOverviewViewModel?,
        viewState: ProductSeriesOverviewViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        checkedProductSeries = { viewModel?.checkedProductSeries(it) },
        navigateToNotification = { viewModel?.navigateToNotification() },
    )

    @Composable
    internal fun ContentView(
        viewState: ProductSeriesOverviewViewState,
        checkedProductSeries: (String) -> Unit,
        navigateToNotification: () -> Unit,
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
                            onClick = { checkedProductSeries(product.seriesId) },
                            checkedProductSeries = checkedProductSeries,
                        )
                    }
                }

                Buttons.PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(resource = Res.string.send_notification),
                    onClick = { navigateToNotification() }
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
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CoilImage(
                        imageModel = { item.imageLink },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        ),
                        modifier = Modifier.size(64.dp)
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
}

// region previews
@Preview
@Composable
fun ProductSeriesOverviewScreenContentPreview() {
    ProductSeriesOverviewScreen().PreviewContent(
        viewState = ProductSeriesOverviewViewState(),
    )
}
// endregion