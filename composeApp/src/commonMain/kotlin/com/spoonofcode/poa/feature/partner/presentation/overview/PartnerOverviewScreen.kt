package com.spoonofcode.poa.feature.partner.presentation.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Cards
//import com.spoonofcode.core.presentation.compose.Chips.HorizontalScrollableFilterChipRow
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partners

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

//            HorizontalScrollableFilterChipRow(
//                categories = viewState.partnerCategories,
//                selectedCategories = viewState.selectedPartnerCategories,
//                onCategorySelected = { onAction(PartnerOverviewViewAction.ClickCategory(it)) }
//            )

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
        Cards.ElevatedCard(
            onClick = onClick,
            modifier = modifier.fillMaxWidth().height(140.dp),
        ) {
            Images.ImageLink(imageLink = item.imageLink!!)
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
