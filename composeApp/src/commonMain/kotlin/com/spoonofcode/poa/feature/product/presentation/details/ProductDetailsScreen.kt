package com.spoonofcode.poa.feature.product.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.network.getUrlOpener
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.compose.video.VideoAnimation
import com.spoonofcode.core.presentation.ext.koinViewModel
import poa.core.presentation.generated.resources.Res
import poa.core.presentation.generated.resources.about_product
import poa.core.presentation.generated.resources.add_product
import poa.core.presentation.generated.resources.learn_more
import poa.core.presentation.generated.resources.product
import poa.core.presentation.generated.resources.visit_product_page
import org.jetbrains.compose.resources.stringResource

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
                onClick = { onAction(ProductDetailsViewAction.EditProduct) },
            ),
            TopBarAction(Icons.Default.Delete, "Delete") {
                onAction(ProductDetailsViewAction.DeleteProductFromUser)
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
                Images.ImageLink(imageLink = product.imageLink)
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Texts.LLB(text = product.collectionName.uppercase())

                Texts.HSB(
                    text = product.name,
                    color = MaterialTheme.colorScheme.onSurface
                )

                product.tagId?.let { tag ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                Icons.Default.QrCode,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Texts.LSB(
                                text = "TAG: $tag",
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }

                Cards.DescriptionCard(
                    title = stringResource(Res.string.about_product),
                    description = product.description
                )

                if (!product.websiteLink.isNullOrBlank() || !product.customLink.isNullOrBlank()) {
                    Cards.ElevatedCard {
                        Column {
                            ClickableInfoRow(
                                text = stringResource(Res.string.visit_product_page),
                                url = product.websiteLink,
                                icon = Icons.Default.Language
                            )
                            if (!product.websiteLink.isNullOrBlank() && !product.customLink.isNullOrBlank()) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )
                            }
                            ClickableInfoRow(
                                text = stringResource(Res.string.learn_more),
                                url = product.customLink,
                                icon = Icons.Default.Link
                            )
                        }
                    }
                }

                if (!hasOwnerUser) {
                    Buttons.PrimaryButton(
                        text = stringResource(Res.string.add_product),
                        onClick = { onAction(ProductDetailsViewAction.AddProductToUser) },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    private fun ClickableInfoRow(
        text: String,
        url: String?,
        icon: ImageVector
    ) {
        if (url.isNullOrBlank()) return
        val urlOpener = remember { getUrlOpener() }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { urlOpener.openUrl(url) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Texts.BMB(
                    text = text,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Texts.LS(
                    text = url,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// region previews
@Preview
@Composable
fun ProductDetailsScreenContentPreview() {
    ProductDetailsScreen(
        productId = "1"
    ).PreviewContent(
        viewState = ProductDetailsViewState(),
    )
}
// endregion
