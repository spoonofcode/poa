package com.spoonofcode.feature.partner.presentation.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Chips.HorizontalScrollableFilterChipRow
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.partners
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PartnerOverviewScreen(
    viewModel: PartnerOverviewViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        topBarTitle = stringResource(Res.string.partners),
        verticalScrollEnable = false,
        backNavigationEnable = false,
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: PartnerOverviewViewState,
    onAction: (PartnerOverviewViewAction) -> Unit,
) {
    TextFields.OutlinedSearch(
        value = viewState.searchText,
        onValueChange = { onAction(PartnerOverviewViewAction.ChangeSearchText(it)) },
    )

    HorizontalScrollableFilterChipRow(
        categories = viewState.partnerCategories.map { partnerCategory -> partnerCategory.name },
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

@Composable
private fun PartnerItem(
    item: Partner,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Cards.ElevatedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(180.dp),
    ) {
        item.imageLink?.let { Images.ImageLink(imageLink = it) }
    }
}

// region previews
//@Preview
//@Composable
//fun PartnerOverviewScreenContentPreview() {
//    PartnerOverviewScreen().PreviewContent(
//        viewState = PartnerOverviewViewState(),
//    )
//}
// endregion
