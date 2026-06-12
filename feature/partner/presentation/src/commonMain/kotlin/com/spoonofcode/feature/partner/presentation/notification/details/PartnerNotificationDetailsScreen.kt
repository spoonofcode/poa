package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spoonofcode.core.data.ext.formatedLocalDateTime
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Cards
import com.spoonofcode.core.presentation.compose.Dialogs
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.ext.koinViewModel
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
import org.jetbrains.compose.resources.stringResource

internal class PartnerNotificationDetailsScreen(
    private val notificationId: String,
) : BaseScreen<PartnerNotificationDetailsViewModel, PartnerNotificationDetailsViewState, PartnerNotificationDetailsViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(onAction: (PartnerNotificationDetailsViewAction) -> Unit): () -> Unit =
        { onAction(PartnerNotificationDetailsViewAction.InitView(notificationId = notificationId)) }

    override fun provideTopBarActions(
        viewState: PartnerNotificationDetailsViewState,
        onAction: (PartnerNotificationDetailsViewAction) -> Unit,
    ) = if (viewState.isNotificationEditable) {
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

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerNotificationDetailsViewModel>()

    @Composable
    override fun provideDialogs(
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

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationDetailsViewState,
        onAction: (PartnerNotificationDetailsViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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
                        items = setOf("Example A, Example B, Example C"),
                    )
                }

                Cards.DescriptionCard(
                    title = stringResource(Res.string.title),
                    description = it.title
                )


                Cards.DescriptionCard(
                    title = stringResource(Res.string.text),
                    description = viewState.notification.text
                )

                Cards.DescriptionCard(
                    title = stringResource(Res.string.delivery_date),
                    description = viewState.notification.deliveryDateTime.formatedLocalDateTime()
                )

                Cards.DescriptionCard(
                    title = stringResource(Res.string.expiration_date),
                    description = viewState.notification.expirationDateTime.formatedLocalDateTime()
                )

                viewState.notification.link?.let { description ->
                    Cards.DescriptionCard(
                        title = stringResource(Res.string.link),
                        description = description
                    )
                }
            }
        }
    }
}

// region previews
@Preview
@Composable
fun PartnerNotificationDetailsScreenContentPreview() {
    PartnerNotificationDetailsScreen(
        notificationId = "1"
    ).PreviewContent(
        viewState = PartnerNotificationDetailsViewState(),
    )
}
// endregion
