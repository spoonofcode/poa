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
import com.spoonofcode.poa.resources.send_notification
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerPanelScreen() :
    BaseScreen<PartnerPanelViewModel, PartnerPanelViewState>() {

    override fun provideTopAppBarTitle() = Res.string.partner_panel

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerPanelViewModel>()

    @Composable
    override fun provideContent(
        viewModel: PartnerPanelViewModel?,
        viewState: PartnerPanelViewState,
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        navigateToMyPartners = { viewModel?.navigateToPartnerEdit() },
        navigateToNotificationType = { viewModel?.navigateToNotificationType() },
    )

    @Composable
    private fun ContentView(
        viewState: PartnerPanelViewState,
        navigateToMyPartners: () -> Unit,
        navigateToNotificationType: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.partner_info),
                onClick = navigateToMyPartners
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.send_notification),
                onClick = navigateToNotificationType
            )
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerPanelScreenPreview() {
        PartnerPanelScreen().PreviewContent(
            viewState = PartnerPanelViewState()
        )
    }
    // endregion
}