package com.spoonofcode.feature.product.presentation.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.ElevatedCard
import com.spoonofcode.core.presentation.compose.ImageLink
import com.spoonofcode.core.presentation.compose.OutlinedTextFieldSearch
import com.spoonofcode.core.presentation.compose.SpacerVerticalBetweenFields
import com.spoonofcode.core.presentation.compose.TextLSB
import com.spoonofcode.core.presentation.compose.TextTMB
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.my_products
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.my_products),
    verticalScrollEnable = false,
    backNavigationEnable = false,
)

@Composable
internal fun ProductOverviewScreen(
    viewModel: ProductOverviewViewModel,
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
    viewState: ProductOverviewViewState,
    onAction: (ProductOverviewViewAction) -> Unit,
) {
    OutlinedTextFieldSearch(
        value = viewState.searchText,
        onValueChange = { onAction(ProductOverviewViewAction.ChangeSearchText(it)) },
    )

    SpacerVerticalBetweenFields()

    ProductGrid(
        products = viewState.filteredProducts,
        onProductClick = { onAction(ProductOverviewViewAction.SelectProduct(it.id)) }
    )
}

@Composable
private fun ProductGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products, key = { it.id }) { product ->
            ProductCard(
                product = product,
                onClick = { onProductClick(product) },
            )
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    ElevatedCard(
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            product.imageLink?.let { ImageLink(imageLink = it) }
        }

        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            product.seriesName?.let {
                TextLSB(
                    text = it.uppercase(),
                    maxLines = 1,
                )
            }
            product.name?.let {
                TextTMB(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun ProductOverviewScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            ProductOverviewViewState(
                initProducts = listOf(
                    Product(
                        id = "1",
                        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
                        name = "Product #1",
                        description = "Przykładowy opis wydarzenia nr 1",
                        seriesId = "1",
                        seriesName = "Series 1",
                        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
                        customLink = "https://beautysaute.pl/",
                        partnerId = "1",
                    )
                ),
                filteredProducts = listOf(
                    Product(
                        id = "1",
                        createdAt = LocalDateTime(2023, 1, 1, 1, 1),
                        name = "Product #1",
                        description = "Przykładowy opis wydarzenia nr 1",
                        seriesId = "1",
                        seriesName = "Series 1",
                        imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
                        customLink = "https://beautysaute.pl/",
                        partnerId = "1",
                    )
                ),
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
