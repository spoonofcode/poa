package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.data.ext.formatedLocalDateTime
import com.spoonofcode.core.presentation.base.StandardScreen
import com.spoonofcode.core.presentation.base.StandardScreenConfig
import com.spoonofcode.core.presentation.base.StandardScreenPreview
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.DescriptionCard
import com.spoonofcode.core.presentation.compose.Dialogs
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.cancel
import com.spoonofcode.feature.partner.presentation.delete_notification
import com.spoonofcode.feature.partner.presentation.delete_notification_text
import com.spoonofcode.feature.partner.presentation.delete_notification_title
import com.spoonofcode.feature.partner.presentation.delivery_date
import com.spoonofcode.feature.partner.presentation.expiration_date
import com.spoonofcode.feature.partner.presentation.link
import com.spoonofcode.feature.partner.presentation.notification
import com.spoonofcode.feature.partner.presentation.notification.compose.StaticSelectedItemsPanel
import com.spoonofcode.feature.partner.presentation.selected_customers
import com.spoonofcode.feature.partner.presentation.selected_series
import com.spoonofcode.feature.partner.presentation.send_notification_to_all_my_clients
import com.spoonofcode.feature.partner.presentation.text
import com.spoonofcode.feature.partner.presentation.title
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
private fun screenConfig(): StandardScreenConfig = StandardScreenConfig().copy(
    topBarTitle = stringResource(Res.string.notification),
)

@Composable
internal fun PartnerNotificationDetailsScreen(
    viewModel: PartnerNotificationDetailsViewModel,
) {
    StandardScreen(
        viewModel = viewModel,
        screenConfig = screenConfig(),
        topBarActions = { viewState, onAction ->
            if (viewState.isNotificationEditable) {
                listOf(
                    TopBarAction(
                        icon = Icons.Default.Edit,
                        description = "Edit",
                        onClick = { onAction(PartnerNotificationDetailsViewAction.EditNotification) },
                    ),
                    TopBarAction(
                        icon = Icons.Default.Delete,
                        description = "Delete",
                        onClick = {
                            onAction(PartnerNotificationDetailsViewAction.DeleteNotification)
                        }
                    )
                )
            } else {
                emptyList()
            }
        },
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
    viewState: PartnerNotificationDetailsViewState,
    onAction: (PartnerNotificationDetailsViewAction) -> Unit,
) {
    viewState.notification?.let {
        if (viewState.notification.topics.isNotEmpty()) {
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

        DescriptionCard(
            title = stringResource(Res.string.title),
            description = it.title
        )


        DescriptionCard(
            title = stringResource(Res.string.text),
            description = viewState.notification.text
        )

        DescriptionCard(
            title = stringResource(Res.string.delivery_date),
            description = viewState.notification.deliveryDateTime.formatedLocalDateTime()
        )

        DescriptionCard(
            title = stringResource(Res.string.expiration_date),
            description = viewState.notification.expirationDateTime.formatedLocalDateTime()
        )

        viewState.notification.link?.let { description ->
            DescriptionCard(
                title = stringResource(Res.string.link),
                description = description
            )
        }
    }
}

@Composable
private fun Dialogs(
    viewState: PartnerNotificationDetailsViewState,
    onAction: (PartnerNotificationDetailsViewAction) -> Unit
) {
    if (viewState.isDeleteNotificationDialogVisible) {
        Dialogs.AlertDialog(
            title = stringResource(Res.string.delete_notification_title),
            text = stringResource(Res.string.delete_notification_text),
            confirmButtonText = stringResource(Res.string.delete_notification),
            dismissButtonText = stringResource(Res.string.cancel),
            confirmAction = { onAction(PartnerNotificationDetailsViewAction.ConfirmDeleteNotification) },
            dismissAction = { onAction(PartnerNotificationDetailsViewAction.CancelDeleteNotification) }
        )
    }
}

// region previews
@Preview
@Composable
private fun PartnerNotificationDetailsScreenContentPreview() {
    StandardScreenPreview(
        viewState = ViewState.Content(
            PartnerNotificationDetailsViewState(
                notification = PartnerNotification(
                    id = "1",
                    title = "Title",
                    text = "Text",
                    deliveryDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = listOf("global"),
                    ownerId = "owner"
                ),
                selectedNames = emptySet(),
                notificationType = PartnerNotificationType.global,
            )
        ),
        screenConfig = screenConfig(),
    ) { viewState, onAction ->
        Content(
            viewState = viewState,
            onAction = onAction,
        )
    }
}
// endregion
