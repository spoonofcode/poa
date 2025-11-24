package com.spoonofcode.poa.feature.product.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.network.getUrlOpener
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.compose.VideoAnimation
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.add_product
import com.spoonofcode.poa.resources.product
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal data class ProductDetailsScreen(
    val productId: String,
    val hasOwnerUser: Boolean = true,
) : BaseScreen<ProductDetailsViewModel, ProductDetailsViewState, ProductDetailsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.product

    @Composable
    override fun provideViewModel() = koinViewModel<ProductDetailsViewModel>()

    override fun provideTopBarActions(
        onAction: (ProductDetailsViewAction) -> Unit,
    ) = if (hasOwnerUser) {
        listOf(
            TopBarAction(
                icon = Icons.Default.Edit,
                description = "Edit",
                onClick = { onAction(ProductDetailsViewAction.AddProduct) },
            ),
            TopBarAction(Icons.Default.Delete, "Delete") {
                onAction(
                    ProductDetailsViewAction.DeleteProduct
                )
            }
        )
    } else {
        emptyList()
    }

    override fun provideInitAction(onAction: (ProductDetailsViewAction) -> Unit): () -> Unit =
        { onAction(ProductDetailsViewAction.InitView(productId = productId)) }

    @Composable
    override fun provideContent(
        viewState: ProductDetailsViewState,
        onAction: (ProductDetailsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val product = requireNotNull(viewState.product)

            if (product.videoLink != null) {
                VideoAnimation(product.videoLink)
            } else {
                RoundedImage(imageLink = product.imageLink)
            }

            viewState.product.tagId?.let { Texts.BLB(it) }

            Spacers.VerticalBetweenFields()

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Texts.BLB(text = product.name)
                    Spacers.VerticalBetweenFields()
                    Texts.BM(text = product.description)
                }

            }

            Spacers.VerticalBetweenFields()

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ClickableInfoRow(
                    url = product.websiteLink,
                )

                HorizontalDivider()

                ClickableInfoRow(
                    url = product.customLink,
                )
            }

            if (hasOwnerUser.not()) {
                Buttons.PrimaryButton(
                    text = stringResource(Res.string.add_product),
                    onClick = { onAction(ProductDetailsViewAction.AddProduct) }
                )
            }
        }
    }

    @Composable
    private fun ClickableInfoRow(
        url: String?,
    ) {
        if (url.isNullOrBlank()) return

        val urlOpener = remember { getUrlOpener() }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = { urlOpener.openUrl(url) })
                .padding(horizontal = 12.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 1.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Texts.BMB(text = url)

                Spacers.Weight1(this)

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = "Open link",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
fun ProductDetailsScreenContentPreview() {
    ProductDetailsScreen(productId = "1").PreviewContent(
        viewState = ProductDetailsViewState(),
    )
}
// endregion