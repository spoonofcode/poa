package com.spoonofcode.poa.feature.product.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
import com.spoonofcode.poa.resources.custom_link
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
            val product = requireNotNull(viewState.product)

            if (product.videoLink != null) {
                ProductVideoAnimation(product.videoLink)
            } else {
                CoilImage(
                    imageModel = { product.imageLink },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                )
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
                    icon = Icons.Default.Link,
                    label = stringResource(Res.string.website_link),
                    value = product.websiteLink,
                    onClick = { }
                )

                HorizontalDivider()

                ClickableInfoRow(
                    icon = Icons.Default.Schema,
                    label = stringResource(Res.string.custom_link),
                    value = product.customLink,
                    onClick = { }
                )
            }

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
    fun ProductVideoAnimation(videoLink: String) {
        Box {
            LoopingVideo(
                url = videoLink,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                mute = true,
            )
        }
    }

    @Composable
    private fun ClickableInfoRow(
        icon: ImageVector,
        label: String,
        value: String?,
        onClick: () -> Unit,
    ) {
        if (value.isNullOrBlank()) return

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 1.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.Default.OpenInNew,
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
    ProductDetailsScreen(productId = 1).PreviewContent(
        viewState = ProductDetailsViewState(),
    )
}
// endregion