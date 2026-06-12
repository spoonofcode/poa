package com.spoonofcode.feature.partner.presentation.notification.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.base.BaseScreen
import com.spoonofcode.core.presentation.compose.Buttons
import com.spoonofcode.core.presentation.compose.DatePickers
import com.spoonofcode.core.presentation.compose.Dialogs
import com.spoonofcode.core.presentation.compose.Spacers
import com.spoonofcode.core.presentation.compose.TextFields
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.ext.koinViewModel
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.presentation.Res
import com.spoonofcode.feature.partner.presentation.cancel
import com.spoonofcode.feature.partner.presentation.delivery_date
import com.spoonofcode.feature.partner.presentation.expiration_date
import com.spoonofcode.feature.partner.presentation.link
import com.spoonofcode.feature.partner.presentation.notification
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
    val selectedTopics: Set<String> = emptySet(),
    val selectedNames: Set<String> = emptySet(),
) : BaseScreen<PartnerNotificationEditViewModel, PartnerNotificationEditViewState, PartnerNotificationEditViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(onAction: (PartnerNotificationEditViewAction) -> Unit): () -> Unit =
        {
            onAction(
                PartnerNotificationEditViewAction.InitView(
                    selectedTopics = selectedTopics,
                    selectedNames = selectedNames,
                )
            )
        }

    @Composable
    override fun provideViewModel() = koinViewModel<PartnerNotificationEditViewModel>()

    @Composable
    override fun provideContent(
        viewState: PartnerNotificationEditViewState,
        onAction: (PartnerNotificationEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Dialogs(viewState, onAction)

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
//                minDateTime = viewState.minDeliveryDateTime,
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
                text = stringResource(resource = Res.string.send_notification),
                onClick = { onAction(PartnerNotificationEditViewAction.SendNotification) }
            )
        }
    }

    @Composable
    fun StaticSelectedItemsPanel(
        title: String,
        items: Set<String>,
        labelFor: (String) -> String = { it },
        modifier: Modifier = Modifier,
    ) {
        if (items.isEmpty()) return

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Texts.TM(text = title)

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Texts.LM(text = items.size.toString())
                }
            }

            Spacer(Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { id ->
                    StaticChip(
                        text = labelFor(id),
                    )
                }
            }
        }
    }

    @Composable
    private fun StaticChip(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = CircleShape,
            tonalElevation = 1.dp,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Texts.LL(text = text)
            }
        }
    }

    @Composable
    private fun Dialogs(
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
