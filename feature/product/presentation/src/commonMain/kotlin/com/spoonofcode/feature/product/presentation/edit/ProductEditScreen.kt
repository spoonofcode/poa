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
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.DescriptionCard
import com.spoonofcode.core.presentation.compose.ImageLink
import com.spoonofcode.core.presentation.compose.OutlinedTextFieldStandard
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.TextHSB
import com.spoonofcode.core.presentation.compose.TextLLB
import com.spoonofcode.core.presentation.compose.video.VideoAnimation
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.presentation.Res
import com.spoonofcode.feature.product.presentation.about_product
import com.spoonofcode.feature.product.presentation.custom_link
import com.spoonofcode.feature.product.presentation.edit_product
import com.spoonofcode.feature.product.presentation.save_changes
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.edit_product),
)

@Composable
internal fun ProductEditScreen(
    viewModel: ProductEditViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
        isViewEnable = { it.isViewEnable },
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: ProductEditViewState,
    onAction: (ProductEditViewAction) -> Unit,
) {
    val product = requireNotNull(viewState.product)

    if (product.videoLink != null) {
        VideoAnimation(
            videoLink = product.videoLink!!,
            imageLink = product.imageLink
        )
    } else {
        product.imageLink?.let { ImageLink(imageLink = it) }
    }

    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        product.seriesName?.let {
            TextLLB(
                text = it.uppercase(),
            )
        }

        product.name?.let {
            TextHSB(
                text = it,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        product.description?.let {
            DescriptionCard(
                title = stringResource(Res.string.about_product),
                description = it
            )
        }

        OutlinedTextFieldStandard(
            value = product.customLink ?: "",
            onValueChange = { onAction(ProductEditViewAction.ChangeCustomLink(it)) },
            innerLabel = stringResource(resource = Res.string.custom_link),
        )

        PrimaryButton(
            text = stringResource(Res.string.save_changes),
            onClick = { onAction(ProductEditViewAction.SaveChanges) },
            modifier = Modifier.padding(top = 8.dp),
            enabled = viewState.isSaveChangesButtonEnabled,
            isLoading = viewState.isSaveChangesButtonLoading,
        )
    }
}

// region previews
@Preview
@Composable
private fun ProductEditScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            ProductEditViewState(
                product = Product(
                    id = "1",
                    productType = "item",
                    ownerEmail = "user1@exmaple.com",
                    partnerId = "1",
                    name = "Product #1",
                    description = "Przykładowy opis wydarzenia nr 1",
                    seriesId = "1",
                    seriesName = "Series 1",
                    createdAt = LocalDateTime(2023, 1, 1, 1, 1),
                    imageLink = "https://beautysaute.pl/environment/cache/images/750_750_productGfx_261/bluza-damska-z-kapturem-ocieplana-bordo.webp",
                    customLink = "https://beautysaute.pl/",
                )
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
