package com.spoonofcode.poa.feature.product.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Cards
import com.spoonofcode.poa.core.ui.compose.Images
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.VideoAnimation
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.about_product
import com.spoonofcode.poa.resources.custom_link
import com.spoonofcode.poa.resources.edit_product
import com.spoonofcode.poa.resources.save_changes
import org.jetbrains.compose.resources.stringResource

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
                Images.RoundedImage(imageLink = product.imageLink)
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 2. Title & Collection Section
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Texts.LLB(
                        text = product.collectionName.uppercase(),
                    )

                    Texts.HSB(
                        text = product.name,
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
                                Texts.LS(
                                    text = "TAG: $tag",
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }

                Cards.DescriptionCard(
                    title = stringResource(Res.string.about_product),
                    description = product.description
                )

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
