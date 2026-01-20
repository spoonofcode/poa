package com.spoonofcode.poa.feature.product.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.VideoAnimation
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.about_product
import com.spoonofcode.poa.resources.custom_link
import com.spoonofcode.poa.resources.details
import com.spoonofcode.poa.resources.edit_product
import com.spoonofcode.poa.resources.save_changes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProductEditScreen(
    val productId: String,
) : BaseScreen<ProductEditViewModel, ProductEditViewState, ProductEditViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.edit_product

    override fun provideInitAction(onAction: (ProductEditViewAction) -> Unit): () -> Unit =
        { onAction(ProductEditViewAction.InitView(productId = productId)) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductEditViewModel>()

    @Composable
    override fun provideContent(
        viewState: ProductEditViewState,
        onAction: (ProductEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val product = requireNotNull(viewState.product)

            // 1. Hero Section (Media)
            if (product.videoLink != null) {
                VideoAnimation(videoLink = product.videoLink)
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
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
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

                // 3. Static Info Card (Name & Description)
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(Res.string.about_product),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacers.VerticalBetweenFields()
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )
                    }
                }

                // 4. Editable Link Section
                TextFields.Outlined(
                    value = product.customLink ?: "",
                    onValueChange = { onAction(ProductEditViewAction.ChangeCustomLink(it)) },
                    innerLabel = stringResource(resource = Res.string.custom_link),
                )

                Buttons.PrimaryButton(
                    text = stringResource(Res.string.save_changes),
                    onClick = { onAction(ProductEditViewAction.SaveChanges) },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

// region previews
@Preview
@Composable
fun ProductEditScreenContentPreview() {
    ProductEditScreen(
        productId = "1",
    ).PreviewContent(
        viewState = ProductEditViewState(),
    )
}
// endregion
