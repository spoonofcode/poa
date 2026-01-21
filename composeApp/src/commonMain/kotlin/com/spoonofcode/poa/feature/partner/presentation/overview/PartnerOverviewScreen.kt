package com.spoonofcode.poa.feature.partner.presentation.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.core.ui.base.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Chips.HorizontalScrollableFilterChipRow
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partners
import com.spoonofcode.poa.resources.search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<PartnerOverviewViewModel, PartnerOverviewViewState, PartnerOverviewViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partners

    override fun provideInitAction(onAction: (PartnerOverviewViewAction) -> Unit): () -> Unit =
        { onAction(PartnerOverviewViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerOverviewViewState,
        onAction: (PartnerOverviewViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.OutlinedSearch(
                value = viewState.searchText,
                onValueChange = { onAction(PartnerOverviewViewAction.ChangeSearchText(it)) },
            )

            HorizontalScrollableFilterChipRow(
                categories = viewState.partnerCategories,
                selectedCategories = viewState.selectedPartnerCategories,
                onCategorySelected = { onAction(PartnerOverviewViewAction.ClickCategory(it)) }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.filteredPartners) { partner ->
                    PartnerItem(
                        item = partner,
                        onClick = { onAction(PartnerOverviewViewAction.SelectPartner(partner.id)) }
                    )
                }
            }
        }
    }

    @Composable
    fun PartnerItem(
        item: Partner,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Card(
            onClick = onClick,
            modifier = modifier.fillMaxWidth().height(140.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        ) {
            CoilImage(
                imageModel = { item.imageLink ?: "https://images.seeklogo.com/logo-png/9/1/nike-logo-png_seeklogo-99478.png" },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large),
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                failure = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Błąd ładowania")
                    }
                }
            )
        }
    }
}

// region previews
@Preview
@Composable
fun PartnerOverviewScreenContentPreview() {
    PartnerOverviewScreen().PreviewContent(
        viewState = PartnerOverviewViewState(),
    )
}
// endregion
