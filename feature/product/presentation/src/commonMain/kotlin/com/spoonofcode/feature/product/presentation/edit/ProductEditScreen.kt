package com.spoonofcode.feature.product.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.compose.video.VideoAnimation
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.about_product
import com.spoonofcode.feature.product.presentation.custom_link
import com.spoonofcode.feature.product.presentation.edit_product
import com.spoonofcode.feature.product.presentation.save_changes
import org.jetbrains.compose.resources.stringResource

internal class ProductEditScreen(
    val productId: String,
) : BaseScreen<ProductEditViewModel, ProductEditViewState, ProductEditViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.edit_product)

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
                VideoAnimation(videoLink = product.videoLink!!)
            } else {
                product.imageLink?.let { Images.ImageLink(imageLink = it) }
            }

            Column(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                product.seriesName?.let {
                    Texts.LLB(
                        text = it.uppercase(),
                    )
                }

                product.name?.let {
                    Texts.HSB(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                product.description?.let {
                    Cards.DescriptionCard(
                        title = stringResource(Res.string.about_product),
                        description = it
                    )
                }

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
