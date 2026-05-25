package com.spoonofcode.feature.partner.presentation.panel

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
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons.IconTextButton
import com.spoonofcode.core.presentation.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.partner_info
import com.spoonofcode.feature.partner.presentation.partner_panel
import com.spoonofcode.feature.partner.presentation.reports
import com.spoonofcode.feature.partner.presentation.send_notification

internal class PartnerPanelScreen() :
    BaseScreen<PartnerPanelViewModel, PartnerPanelViewState, PartnerPanelViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.partner_panel

    override fun provideInitAction(onAction: (PartnerPanelViewAction) -> Unit): () -> Unit =
        { onAction(PartnerPanelViewAction.InitView) }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerPanelViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerPanelViewState,
        onAction: (PartnerPanelViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    icon = Icons.Default.Person,
                    text = stringResource(resource = Res.string.partner_info),
                    onClick = { onAction(PartnerPanelViewAction.NavigateToPartnerEdit) },
                    modifier = Modifier.weight(1f)
                )

                IconTextButton(
                    icon = Icons.AutoMirrored.Filled.Send,
                    text = stringResource(resource = Res.string.send_notification),
                    onClick = { onAction(PartnerPanelViewAction.NavigateToNotificationType) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconTextButton(
                    enabled = false,
                    icon = Icons.Default.BarChart,
                    text = stringResource(resource = Res.string.reports),
                    onClick = { onAction(PartnerPanelViewAction.NavigateToReports) },
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
        PartnerPanelScreen()
            .PreviewContent(
            viewState = PartnerPanelViewState()
        )
    }
    // endregion
}