package com.spoonofcode.poa.feature.product.series.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
import org.jetbrains.compose.resources.stringResource

internal class ProductSeriesOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = true,
) : BaseScreen<ProductSeriesOverviewViewModel, ProductSeriesOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.my_product_series

    override fun provideReloadAction(viewModel: ProductSeriesOverviewViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductSeriesOverviewViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ProductSeriesOverviewViewModel,
        viewState: ProductSeriesOverviewViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            checkedProductSeries = { viewModel.checkedProductSeries(it) },
            navigateToNotification = { viewModel.navigateToNotification() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ProductSeriesOverviewViewState,
        checkedProductSeries: (String) -> Unit,
        navigateToNotification: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.productSeries) { product ->

                    val checkedProductSeries =
                        viewState.checkedProductSeriesIds[product.seriesId] ?: false

                    ProductSeriesItem(
                        item = product,
                        onClick = { checkedProductSeries(product.seriesId) },
                        checkedProductSeries = checkedProductSeries,
                    )
                }
                item {
                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.send_notification),
                        onClick = { navigateToNotification() }
                    )
                }
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
                    Texts.BLB(
                        text = item.seriesId,
                    )
                    Spacers.HorizontalBetweenFields()
                    Checkbox(
                        checked = checkedProductSeries,
                        onCheckedChange = { onClick() }
                    )
                }
            }
        }
    }

    @Composable
    private fun ProductItemRowElement(
        elementValue: String,
        icon: ImageVector,
        contentDescription: String,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
//                    checkedStates[item] = !checked
                }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
            Spacers.HorizontalBetweenFields()
            Texts.BM(
                text = elementValue,
            )
        }
    }

    @Composable
    fun CheckboxListExample() {
        // Sample data
        val items = listOf("Milk", "Eggs", "Bread", "Cheese", "Butter")
        // Track checkbox states
        val checkedStates = remember { mutableStateMapOf<String, Boolean>() }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Shopping List", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                val checked = checkedStates[item] ?: false
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            checkedStates[item] = !checked
                        }
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            checkedStates[item] = it
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(item)
                }
            }
        }
    }
}