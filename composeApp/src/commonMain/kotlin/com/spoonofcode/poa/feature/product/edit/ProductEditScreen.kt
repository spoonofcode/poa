package com.spoonofcode.poa.feature.product.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.RoundedImage
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.VideoAnimation
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.custom_link
import com.spoonofcode.poa.resources.edit_product
import com.spoonofcode.poa.resources.save_changes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class ProductEditScreen(
    val productId: String,
) : BaseScreen<ProductEditViewModel, ProductEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.edit_product

    override fun provideInitAction(viewModel: ProductEditViewModel?): () -> Unit =
        { viewModel?.initView(productId = productId) }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProductEditViewModel?,
        viewState: ProductEditViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        changeCustomLink = { viewModel?.changeCustomLink(it) },
        saveChanges = { viewModel?.saveChanges() }
    )

    @Composable
    private fun ContentView(
        viewState: ProductEditViewState,
        changeCustomLink: (String) -> Unit,
        saveChanges: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {

            val product = requireNotNull(viewState.product)

            if (product.videoLink != null) {
                VideoAnimation(videoLink = product.videoLink)
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

            TextFields.Outlined(
                value = product.customLink ?: "",
                onValueChange = changeCustomLink,
                label = stringResource(resource = Res.string.custom_link),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(Res.string.save_changes),
                onClick = saveChanges
            )
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