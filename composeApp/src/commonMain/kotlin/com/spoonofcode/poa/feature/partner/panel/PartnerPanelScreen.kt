package com.spoonofcode.poa.feature.partner.panel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Buttons.IconTextButton
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.my_products
import com.spoonofcode.poa.resources.partner_info
import com.spoonofcode.poa.resources.partner_panel
import com.spoonofcode.poa.resources.send_notification
import com.spoonofcode.poa.resources.to_selected_customers
import com.spoonofcode.poa.resources.to_selected_series
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Person,
                    text = stringResource(resource = Res.string.partner_info),
                    onClick = navigateToMyPartners,
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.AutoMirrored.Filled.Send,
                    text = stringResource(resource = Res.string.send_notification),
                    onClick = navigateToNotificationType,
                    modifier = Modifier.weight(1f)
                )
            }
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