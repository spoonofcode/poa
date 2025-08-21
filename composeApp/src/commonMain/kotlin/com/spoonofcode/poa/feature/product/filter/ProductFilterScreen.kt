package com.spoonofcode.poa.feature.product.filter

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.apply_filter
import com.spoonofcode.poa.resources.filter_products
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class ProductFilterScreen() :
    BaseScreen<ProductFilterViewModel, ProductFilterViewState>() {

    override fun provideTopAppBarTitle() = Res.string.filter_products

    override fun provideReloadAction(viewModel: ProductFilterViewModel): () -> Unit =
        { viewModel.initView() }

    @Composable
    override fun provideViewModel() = koinViewModel<ProductFilterViewModel>()

    @Composable
    override fun provideContent(
        viewModel: ProductFilterViewModel?,
        viewState: ProductFilterViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            changeCollectionName = { viewModel?.changeCollectionName(it) },
            applyFilter = { viewModel?.applyFilter() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ProductFilterViewState,
        changeCollectionName: (String) -> Unit,
        applyFilter: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
//            DropDownMenus.DropdownMenu(
//                enabled = true,
//                label = stringResource(resource = Res.string.club),
//                value = viewState.clubs.getValue(viewState.selectedClubId),
//                values = viewState.clubs,
//                onValueChange = { changeClub(it) }
//            )

            Spacers.Weight1(this)

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.apply_filter),
                onClick = { applyFilter() }
            )
        }
    }
}