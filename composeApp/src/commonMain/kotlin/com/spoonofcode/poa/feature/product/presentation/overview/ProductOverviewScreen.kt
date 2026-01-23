package com.spoonofcode.poa.feature.product.presentation.overview

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
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Cards
import com.spoonofcode.poa.core.ui.compose.Images
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_products

internal class ProductOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProductOverviewViewModel, ProductOverviewViewState, ProductOverviewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.my_products

    override fun provideInitAction(onAction: (ProductOverviewViewAction) -> Unit): () -> Unit =
        { onAction(ProductOverviewViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewState: ProductOverviewViewState,
        onAction: (ProductOverviewViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.OutlinedSearch(
                value = viewState.searchText,
                onValueChange = { onAction(ProductOverviewViewAction.ChangeSearchText(it)) },
            )

            ProductGrid(
                products = viewState.filteredProducts,
                onProductClick = { onAction(ProductOverviewViewAction.SelectProduct(it.id)) }
            )
        }
    }

    @Composable
    fun ProductGrid(
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
    fun ProductCard(
        product: Product,
        onClick: () -> Unit,
    ) {
        Cards.ElevatedCard(
            onClick = onClick,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Images.ImageLink(imageLink = product.imageLink)
            }

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Texts.LSB(text = product.collectionName.uppercase())
                Texts.TMB(
                    text = product.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
fun ProductOverviewScreenContentPreview() {
    ProductOverviewScreen()
        .PreviewContent(
            viewState = ProductOverviewViewState(),
        )
}
// endregion
