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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProductOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewModel, com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState, com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.my_products

    override fun provideInitAction(onAction: (com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction) -> Unit): () -> Unit =
        { onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState,
        onAction: (com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.ChangeSearchText(it)) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            ProductGrid(
                products = viewState.filteredProducts,
                onProductClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewAction.SelectProduct(it.id)) }
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
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun ProductCard(
        product: Product,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                CoilImage(
                    imageModel = { product.imageLink },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(Modifier.padding(16.dp)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.collectionName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
fun ProductOverviewScreenContentPreview() {
    _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewScreen()
        .PreviewContent(
        viewState = _root_ide_package_.com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewViewState(
            screenState = ScreenState.CONTENT,
        ),
    )
}
// endregion