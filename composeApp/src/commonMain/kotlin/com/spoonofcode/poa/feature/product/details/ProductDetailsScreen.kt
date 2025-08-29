package com.spoonofcode.poa.feature.product.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.LoopingVideo
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.add_product
import com.spoonofcode.poa.resources.collection_name
import com.spoonofcode.poa.resources.custom_link
import com.spoonofcode.poa.resources.description
import com.spoonofcode.poa.resources.name
import com.spoonofcode.poa.resources.product
import com.spoonofcode.poa.resources.website_link
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal data class ProductDetailsScreen(
    val productId: Int,
    val hasOwnerUser: Boolean = true,
) : BaseScreen<ProductDetailsViewModel, ProductDetailsViewState>() {

    override fun provideTopAppBarTitle() = Res.string.product

    @Composable
    override fun provideViewModel() = koinViewModel<ProductDetailsViewModel>()

    override fun provideTopBarActions(
        viewModel: ProductDetailsViewModel?,
    ) = if (hasOwnerUser) {
        listOf(
            TopBarAction(Icons.Default.Edit, "Edit") { viewModel?.editProduct() },
            TopBarAction(Icons.Default.Delete, "Delete") { viewModel?.deleteProduct() }
        )
    } else {
        emptyList()
    }

    override fun provideInitAction(viewModel: ProductDetailsViewModel?): () -> Unit =
        { viewModel?.initView(productId = productId) }

    @Composable
    override fun provideContent(
        viewModel: ProductDetailsViewModel?,
        viewState: ProductDetailsViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        addProduct = { viewModel?.addProduct() },
    )

    @Composable
    internal fun ContentView(
        viewState: ProductDetailsViewState,
        addProduct: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
//            ProductAnimation(viewState.product!!.nftAnimation)

            val product = requireNotNull(viewState.product)

            CoilImage(
                imageModel = { product.imageLink },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

            viewState.product.tagId?.let { Texts.BLB(it) }

            ProductItemRowElement(
                label = Res.string.name,
                elementValue = product.name,
                icon = Icons.Default.Paid,
                contentDescription = "Product name",
            )

            ProductItemRowElement(
                label = Res.string.description,
                elementValue = product.description,
                icon = Icons.Default.WebStories,
                contentDescription = "Series",
            )

            ProductItemRowElement(
                label = Res.string.collection_name,
                elementValue = product.collectionName,
                icon = Icons.Default.Person,
                contentDescription = "Owner",
            )

            ProductItemRowElement(
                label = Res.string.website_link,
                elementValue = product.websiteLink!!,
                icon = Icons.Default.Mail,
                contentDescription = "Owner Mail",
            )

            ProductItemRowElement(
                label = Res.string.custom_link,
                elementValue = product.customLink!!,
                icon = Icons.Default.Schema,
                contentDescription = "Level",
            )

            if (hasOwnerUser.not()) {
                Buttons.PrimaryButton(
                    text = stringResource(Res.string.add_product),
                    onClick = addProduct
                )
            }
        }
    }

    @Composable
    private fun ProductItemRowElement(
        label: StringResource,
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
            Column {
                Texts.BSB(
                    text = stringResource(resource = label),
                )
                Texts.BM(
                    text = elementValue,
                )

            }
        }
        Spacers.VerticalBetweenFields()
    }

    @Composable
    fun ProductAnimation(productAnimation: String) {
        Box {
            LoopingVideo(
                url = productAnimation,
                modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
                mute = true      // silent background animation
            )
        }
    }
}

// region previews
@Preview
@Composable
fun ProductDetailsScreenContentPreview() {
    ProductDetailsScreen(productId = 1).PreviewContent(
        viewState = ProductDetailsViewState(),
    )
}
// endregion