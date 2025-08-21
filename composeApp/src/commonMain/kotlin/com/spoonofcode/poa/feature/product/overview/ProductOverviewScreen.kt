package com.spoonofcode.poa.feature.product.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.search
import org.jetbrains.compose.resources.stringResource

internal class ProductOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<ProductOverviewViewModel, ProductOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.my_products

    override fun provideReloadAction(viewModel: ProductOverviewViewModel): () -> Unit =
        { viewModel.initView() }


    override fun provideTopBarActions(
        viewModel: ProductOverviewViewModel?,
    ) = listOf(
        TopBarAction(
            icon = Icons.Default.FilterList,
            description = "filter",
            onClick = { viewModel?.navigateToFilter() }
        ),
    )

    @Composable
    override fun provideViewModel() = koinViewModel<ProductOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProductOverviewViewModel?,
        viewState: ProductOverviewViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            changeSearchText = { viewModel?.changeSearchText(it) },
            selectProduct = { viewModel?.selectProduct(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ProductOverviewViewState,
        changeSearchText: (String) -> Unit,
        selectProduct: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.filteredProducts) { product ->
                    ProductItem(
                        item = product,
                        onClick = { selectProduct(product.id) }
                    )
                }
            }
        }
    }

    @Composable
    fun ProductItem(
        item: Product,
        onClick: () -> Unit,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
            ) {
                Texts.BLB(
                    text = item.tagId,
                )
                ProductItemRowElement(
                    elementValue = item.name,
                    icon = Icons.Default.Paid,
                    contentDescription = "Name",
                )

                ProductItemRowElement(
                    elementValue = item.collectionName,
                    icon = Icons.Default.WebStories,
                    contentDescription = "Collection name",
                )

                ProductItemRowElement(
                    elementValue = item.tagId,
                    icon = Icons.Default.Schema,
                    contentDescription = "Tag id",
                )
            }
        }
    }

    @Composable
    private fun ProductItemRowElement(
        elementValue: String,
        icon: ImageVector,
        contentDescription: String,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
}