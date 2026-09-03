package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.StandardDatePickerWithTimer
import com.spoonofcode.core.presentation.compose.StandardAlertDialog
import com.spoonofcode.core.presentation.compose.PrimaryButton
import com.spoonofcode.core.presentation.compose.SpacerWeight1
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.cancel
import com.spoonofcode.feature.partner.presentation.delivery_date
import com.spoonofcode.feature.partner.presentation.expiration_date
import com.spoonofcode.feature.partner.presentation.link
import com.spoonofcode.feature.partner.presentation.notification
import com.spoonofcode.feature.partner.presentation.notification.compose.StaticSelectedItemsPanel
import com.spoonofcode.feature.partner.presentation.save_changes
import com.spoonofcode.feature.partner.presentation.selected_customers
import com.spoonofcode.feature.partner.presentation.selected_series
import com.spoonofcode.feature.partner.presentation.send_notification
import com.spoonofcode.feature.partner.presentation.send_notification_dialog_text
import com.spoonofcode.feature.partner.presentation.send_notification_dialog_title
import com.spoonofcode.feature.partner.presentation.send_notification_to_all_my_clients
import com.spoonofcode.feature.partner.presentation.text
import com.spoonofcode.feature.partner.presentation.title
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.notification),
)

@Composable
internal fun PartnerNotificationEditScreen(
    viewModel: PartnerNotificationEditViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
        dialogs = { viewState, onAction ->
            Dialogs(viewState, onAction)
        }
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}

@Composable
private fun ColumnScope.Content(
    viewState: PartnerNotificationEditViewState,
    onAction: (PartnerNotificationEditViewAction) -> Unit,
) {
    if (viewState.selectedTopics.isNotEmpty()) {
        val title = when (viewState.notificationType) {
            PartnerNotificationType.user -> stringResource(Res.string.selected_customers)
            PartnerNotificationType.serie -> stringResource(Res.string.selected_series)
            PartnerNotificationType.partner -> stringResource(Res.string.send_notification_to_all_my_clients)
            else -> ""
        }

        StaticSelectedItemsPanel(
            title = title,
            items = viewState.selectedNames,
        )
    }

    TextFields.Outlined(
        value = viewState.title,
        onValueChange = { onAction(PartnerNotificationEditViewAction.ChangeTitle(it)) },
        label = stringResource(resource = Res.string.title),
    )

    TextFields.Outlined(
        value = viewState.text,
        onValueChange = { onAction(PartnerNotificationEditViewAction.ChangeText(it)) },
        label = stringResource(resource = Res.string.text),
        minLines = 8,
    )

    StandardDatePickerWithTimer(
        label = stringResource(resource = Res.string.delivery_date),
        value = viewState.deliveryDateTime,
        minDateTime = viewState.minDeliveryDateTime,
        onValueChange = {
            onAction(
                PartnerNotificationEditViewAction.ChangeDeliveryDateTime(
                    it
                )
            )
        },
        modifier = Modifier,
    )

    StandardDatePickerWithTimer(
        label = stringResource(resource = Res.string.expiration_date),
        value = viewState.expirationDateTime,
        minDateTime = viewState.minExpirationDateTime,
        onValueChange = {
            onAction(
                PartnerNotificationEditViewAction.ChangeExpirationDateTime(
                    it
                )
            )
        },
        modifier = Modifier,
    )

    TextFields.Outlined(
        value = viewState.link,
        onValueChange = { onAction(PartnerNotificationEditViewAction.ChangeLink(it)) },
        label = stringResource(resource = Res.string.link),
    )

    SpacerWeight1(this)

    PrimaryButton(
        text = if (viewState.notificationId == null) {
            stringResource(resource = Res.string.send_notification)
        } else {
            stringResource(resource = Res.string.save_changes)
        },
        onClick = { onAction(PartnerNotificationEditViewAction.SendNotification) }
    )
}

@Composable
private fun Dialogs(
    viewState: PartnerNotificationEditViewState,
    onAction: (PartnerNotificationEditViewAction) -> Unit
) {
    if (viewState.isSendNotificationDialogVisible) {
        StandardAlertDialog(
            title = stringResource(Res.string.send_notification_dialog_title),
            text = stringResource(Res.string.send_notification_dialog_text),
            confirmButtonText = stringResource(Res.string.send_notification),
            dismissButtonText = stringResource(Res.string.cancel),
            confirmAction = { onAction(PartnerNotificationEditViewAction.ConfirmSendNotification) },
            dismissAction = { onAction(PartnerNotificationEditViewAction.CancelSendNotification) }
        )
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationEditScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerNotificationEditViewState(
                notificationType = PartnerNotificationType.global,
                selectedTopics = emptySet(),
                selectedNames = emptySet(),
            )
        ),
        screenConfig = screenConfig(),
        dialogs = { viewState, onAction ->
            Dialogs(viewState, onAction)
        }
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
