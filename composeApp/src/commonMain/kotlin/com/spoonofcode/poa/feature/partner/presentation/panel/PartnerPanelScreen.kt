package com.spoonofcode.poa.feature.partner.presentation.panel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons.IconTextButton
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.partner_info
import com.spoonofcode.poa.resources.partner_panel
import com.spoonofcode.poa.resources.reports
import com.spoonofcode.poa.resources.send_notification
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class PartnerPanelScreen() :
    BaseScreen<com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewModel, com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewState, com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partner_panel

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewState,
        onAction: (com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Person,
                    text = stringResource(resource = Res.string.partner_info),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction.NavigateToPartnerEdit) },
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.AutoMirrored.Filled.Send,
                    text = stringResource(resource = Res.string.send_notification),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction.NavigateToNotificationType) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacers.VerticalBetweenFields()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    enabled = false,
                    icon = Icons.Default.BarChart,
                    text = stringResource(resource = Res.string.reports),
                    onClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewAction.NavigateToReports) },
                    modifier = Modifier.weight(0.5f)
                )

                IconTextButton(
                    enabled = false,
                    icon = Icons.Default.BarChart,
                    text = stringResource(resource = Res.string.reports),
                    onClick = { },
                    modifier = Modifier.weight(0.5f).alpha(0f) // alpha=0f to hide
                )
            }
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedPartnerPanelScreenPreview() {
        _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelScreen()
            .PreviewContent(
            viewState = _root_ide_package_.com.spoonofcode.poa.feature.partner.presentation.panel.PartnerPanelViewState()
        )
    }
    // endregion
}