package com.spoonofcode.poa.feature.partner.panel

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.partner_info
import com.spoonofcode.poa.resources.partner_panel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerPanelScreen() :
    BaseScreen<PartnerPanelViewModel, PartnerPanelViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner_panel

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerPanelViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: PartnerPanelViewModel,
        viewState: PartnerPanelViewState,
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            // --- POPRAWKA ---
            // Używamy nowej, poprawnej nazwy funkcji, która nawiguje do listy partnerów
            navigateToMyPartners = { viewModel.navigateToMyPartners() },
            navigateToPartnerProducts = { viewModel.navigateToPartnerProducts() },
        )
    }

    @Composable
    private fun ContentView(
        viewState: PartnerPanelViewState,
        navigateToMyPartners: () -> Unit, // Zmieniamy nazwę, aby była spójna
        navigateToPartnerProducts: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.partner_info),
                onClick = navigateToMyPartners // <-- Używamy nowej, poprawnej nazwy
            )
            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.my_products),
                onClick = navigateToPartnerProducts
            )
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerPanelScreenPreview() {
        PreviewContent(
            content = ContentView(
                viewState = PartnerPanelViewState(),
                navigateToMyPartners = {}, // <-- Używamy nowej, poprawnej nazwy
                navigateToPartnerProducts = {},
            )
        )
    }
    // endregion
}