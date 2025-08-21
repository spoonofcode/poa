package com.spoonofcode.poa.feature.partner.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.Paddings.innerElevatedCardPadding
import com.spoonofcode.poa.core.ui.Paddings.spaceBetweenListElements
import com.spoonofcode.poa.core.ui.compose.Chips.HorizontalScrollableFilterChipRow
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsScreen
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsViewState
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partners
import com.spoonofcode.poa.resources.search
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerOverviewScreen(
    override val verticalScrollEnable: Boolean = false,
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<PartnerOverviewViewModel, PartnerOverviewViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partners

    override fun provideReloadAction(viewModel: PartnerOverviewViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerOverviewViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerOverviewViewModel?,
        viewState: PartnerOverviewViewState
    ): @Composable ColumnScope.() -> Unit {

        return ContentView(
            viewState = viewState,
            clickCategory = { viewModel?.clickCategory(it) },
            changeSearchText = { viewModel?.changeSearchText(it) },
            selectPartner = { viewModel?.selectPartner(it) },
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerOverviewViewState,
        changeSearchText: (String) -> Unit,
        clickCategory: (Int) -> Unit,
        selectPartner: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            TextFields.Outlined(
                value = viewState.searchText,
                onValueChange = { changeSearchText(it) },
                innerLabel = stringResource(resource = Res.string.search),
            )

            HorizontalScrollableFilterChipRow(
                categories = viewState.partnerCategories,
                selectedCategories = viewState.selectedPartnerCategories,
                onCategorySelected = { categoryId ->
                    clickCategory(categoryId)
                }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spaceBetweenListElements)
            ) {
                items(viewState.filteredPartners) { partner ->
                    PartnerItem(
                        item = partner,
                        onClick = { selectPartner(partner.id) }
                    )
                }
            }
        }
    }

    @Composable
    private fun PartnerItem(
        item: Partner,
        onClick: () -> Unit,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerElevatedCardPadding)
            ) {
                CoilImage(
                    imageModel = { item.imageLink },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerOverviewScreenPreview() {
        PartnerOverviewScreen().PreviewContent(
            viewState = PartnerOverviewViewState(),
        )
    }
    // endregion
}