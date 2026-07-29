package com.spoonofcode.feature.partner.presentation.notification.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.WebStories
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
import com.spoonofcode.feature.partner.presentation.notification_type
import com.spoonofcode.feature.partner.presentation.to_all
import com.spoonofcode.feature.partner.presentation.to_all_my_customers
import com.spoonofcode.feature.partner.presentation.to_selected_customers
import com.spoonofcode.feature.partner.presentation.to_selected_series
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.notification_type),
)

@Composable
internal fun PartnerNotificationTypeScreen(
    viewModel: PartnerNotificationTypeViewModel,
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
    viewState: PartnerNotificationTypeViewState,
    onAction: (PartnerNotificationTypeViewAction) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconTextButton(
            icon = Icons.Default.Groups,
            text = stringResource(resource = Res.string.to_all),
            onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToAllCustomers) },
            modifier = Modifier.weight(1f),
            enabled = viewState.buttonToAllEnable,
        )

        IconTextButton(
            icon = Icons.Default.Diversity3,
            text = stringResource(resource = Res.string.to_all_my_customers),
            onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToAllPartnerCustomers) },
            modifier = Modifier.weight(1f),
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconTextButton(
            icon = Icons.Default.PersonSearch,
            text = stringResource(resource = Res.string.to_selected_customers),
            onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerCustomers) },
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = Icons.Default.WebStories,
            text = stringResource(resource = Res.string.to_selected_series),
            onClick = { onAction(PartnerNotificationTypeViewAction.SendNotificationToSelectedPartnerProductSeries) },
            modifier = Modifier.weight(1f)
        )
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationTypeScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(PartnerNotificationTypeViewState()),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
