package com.spoonofcode.poa.feature.product.presentation.details

import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.poa.core.network.getUrlOpener
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
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
                onClick = { onAction(ProductDetailsViewAction.EditProduct) },
            ),
            TopBarAction(Icons.Default.Delete, "Delete") {
                onAction(
                    ProductDetailsViewAction.DeleteProductFromUser
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

            // 1. Hero Section (Media)
            if (product.videoLink != null) {
                VideoAnimation(product.videoLink)
            } else {
                RoundedImage(imageLink = product.imageLink)
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 2. Title & Collection Section
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = product.collectionName.uppercase(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold
                    )
                    
                    // Tag ID Badge (if exists)
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
                                Text(
                                    text = "TAG: $tag",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }

                // 3. Description Card
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "O produkcie",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacers.VerticalBetweenFields()
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                    }
                }

                // 4. External Links Section
                if (!product.websiteLink.isNullOrBlank() || !product.customLink.isNullOrBlank()) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column {
                            ClickableInfoRow(
                                text = "Odwiedź stronę produktu",
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
                                text = "Dowiedz się więcej",
                                url = product.customLink,
                                icon = Icons.Default.Link
                            )
                        }
                    }
                }

                if (hasOwnerUser.not()) {
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
                .clickable(onClick = { urlOpener.openUrl(url) })
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
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = url,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = "Otwórz link",
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
