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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Chips.HorizontalScrollableFilterChipRow
import com.spoonofcode.core.presentation.compose.Images
import com.spoonofcode.core.presentation.compose.Paddings.spaceBetweenListElements
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.feature.partner.domain.model.Partner
import com.spoonofcode.feature.partner.domain.model.PartnerCategory
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.partners
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.partners),
    verticalScrollEnable = false,
    backNavigationEnable = false,
)

@Composable
internal fun PartnerOverviewScreen(
    viewModel: PartnerOverviewViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
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
@Preview
@Composable
private fun PartnerOverviewScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerOverviewViewState(
                initPartners = listOf(
                    Partner(
                        id = "550e8400-e29b-41d4-a716-446655440001",
                        name = "Beauty Saute",
                        partnerCategory = "Regular",
                        email = "beauty.saute@gmail.com",
                        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
                        imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
                        websiteLink = "https://beautysaute.pl/",
                        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
                        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                    )
                ),
                filteredPartners = listOf(
                    Partner(
                        id = "550e8400-e29b-41d4-a716-446655440001",
                        name = "Beauty Saute",
                        partnerCategory = "Regular",
                        email = "beauty.saute@gmail.com",
                        description = "Beauty Sautè to więcej niż odzież – to filozofia.",
                        imageLink = "https://beautysaute.pl/environment/cache/images/0_0_storefrontImages_11a16d30-d85a-422f-917d-195c30274147.png",
                        websiteLink = "https://beautysaute.pl/",
                        instagramLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        facebookLink = "https://www.facebook.com/profile.php?id=61568636916637",
                        youtubeLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                        xLink = "https://www.instagram.com/beauty_saute_fashion/?igsh=Y3FxN2U3bDRya2F0&utm_source=qr#",
                    )
                ),
                partnerCategories = listOf(
                    PartnerCategory(name = "Regular"),
                    PartnerCategory(name = "Premium"),
                ),
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
