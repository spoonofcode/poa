package com.spoonofcode.feature.partner.presentation.panel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.Buttons.IconTextButton
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.my_notifications
import com.spoonofcode.feature.partner.presentation.partner_info
import com.spoonofcode.feature.partner.presentation.partner_panel
import com.spoonofcode.feature.partner.presentation.reports
import com.spoonofcode.feature.partner.presentation.send_notification
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.partner_panel),
)

@Composable
internal fun PartnerPanelScreen(
    viewModel: PartnerPanelViewModel,
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
    viewState: PartnerPanelViewState,
    onAction: (PartnerPanelViewAction) -> Unit,
) {
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
            enabled = true,
            icon = Icons.Default.Notifications,
            text = stringResource(resource = Res.string.my_notifications),
            onClick = { onAction(PartnerPanelViewAction.NavigateToPartnerNotifications) },
            modifier = Modifier.weight(0.5f)
        )

        IconTextButton(
            enabled = false,
            icon = Icons.Default.BarChart,
            text = stringResource(resource = Res.string.reports),
            onClick = { },
            modifier = Modifier.weight(0.5f)
        )
    }
}

// region previews
@Preview
@Composable
private fun PartnerPanelScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(PartnerPanelViewState()),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
