package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.DatePickers
import com.spoonofcode.core.presentation.compose.Dialogs
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.ext.koinViewModel
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

internal class PartnerNotificationEditScreen(
    val notificationId: String? = null,
    val selectedTopics: Set<String> = emptySet(),
    val selectedNames: Set<String> = emptySet(),
    val viewModel: PartnerNotificationEditViewModel,
) : BaseScreen<PartnerNotificationEditViewModel, PartnerNotificationEditViewState, PartnerNotificationEditViewAction, Nothing>() {

    @Composable
    override fun provideTopAppBarTitle() = stringResource(Res.string.notification)

    override fun provideInitAction(onAction: (PartnerNotificationEditViewAction) -> Unit): () -> Unit =
        {
            onAction(
                PartnerNotificationEditViewAction.InitView(
                    notificationId = notificationId,
                    selectedTopics = selectedTopics,
                    selectedNames = selectedNames,
                )
            )
        }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerNotificationEditViewModel>()

    @Composable
    override fun provideDialogs(
        viewState: PartnerNotificationEditViewState,
        onAction: (PartnerNotificationEditViewAction) -> Unit
    ) {
        if (viewState.isSendNotificationDialogVisible) {
            Dialogs.AlertDialog(
                title = stringResource(Res.string.send_notification_dialog_title),
                text = stringResource(Res.string.send_notification_dialog_text),
                confirmButtonText = stringResource(Res.string.send_notification),
                dismissButtonText = stringResource(Res.string.cancel),
                confirmAction = { onAction(PartnerNotificationEditViewAction.ConfirmSendNotification) },
                dismissAction = { onAction(PartnerNotificationEditViewAction.CancelSendNotification) }
            )
        }
    }

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationEditViewState,
        onAction: (PartnerNotificationEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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

            DatePickers.DatePickerWithTimer(
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

            DatePickers.DatePickerWithTimer(
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

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = if (viewState.notificationId == null) {
                    stringResource(resource = Res.string.send_notification)
                } else {
                    stringResource(resource = Res.string.save_changes)
                },
                onClick = { onAction(PartnerNotificationEditViewAction.SendNotification) }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationEditScreenContentPreview() {
    PartnerNotificationEditScreen()
        .PreviewContent(
            PartnerNotificationEditViewState()
        )
}
// endregion
