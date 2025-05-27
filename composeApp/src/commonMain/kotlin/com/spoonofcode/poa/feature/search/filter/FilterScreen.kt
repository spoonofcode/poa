package com.spoonofcode.poa.feature.search.filter

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.DropDownMenus
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.apply_filter
import com.spoonofcode.poa.resources.blockchain
import com.spoonofcode.poa.resources.filter_nft
import com.spoonofcode.poa.resources.serie
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class FilterScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.filter_nft,
) : BaseScreen<FilterViewModel, FilterViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<FilterViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: FilterViewModel,
        viewState: FilterViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeNFTSerie = { viewModel.changeNFTSerie(it) },
            changeBlockchain = { viewModel.changeBlockchain(it) },
            applyFilter = { viewModel.applyFilter() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: FilterViewState,
        changeNFTSerie: (String) -> Unit,
        changeBlockchain: (String) -> Unit,
        applyFilter: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.serie),
                value = viewState.nftSeries.getValue(viewState.selectedSerieId),
                values = viewState.nftSeries,
                onValueChange = { changeNFTSerie(it) }
            )
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.blockchain),
                value = viewState.blockchains.getValue(viewState.selectedBlockchainId),
                values = viewState.blockchains,
                onValueChange = { changeBlockchain(it) }
            )

            Spacers.Weight1(this)

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.apply_filter),
                onClick = { applyFilter() }
            )
        }
    }
}