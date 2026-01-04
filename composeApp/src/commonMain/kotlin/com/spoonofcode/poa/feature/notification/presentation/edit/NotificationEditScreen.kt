package com.spoonofcode.poa.feature.notification.presentation.edit

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.DatePickers
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.expiration_date
import com.spoonofcode.poa.resources.link
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.selected_customers
import com.spoonofcode.poa.resources.selected_series
import com.spoonofcode.poa.resources.send_notification
import com.spoonofcode.poa.resources.text
import com.spoonofcode.poa.resources.title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationEditScreen(
    val onlyToSelectedUsers: Boolean = false,
    val topics: List<String> = emptyList(),
    val itemIds: List<String> = emptyList(),
) : BaseScreen<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewModel, com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState, com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(onAction: (com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction) -> Unit): () -> Unit =
        { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.InitView(itemIds = itemIds)) }

    @Composable
    override fun provideViewModel() = koinViewModel<com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewModel>()

    @Composable
    override fun provideContent(
        viewState: com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState,
        onAction: (com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            if (viewState.itemIds.isNotEmpty()) {
                val title = if (onlyToSelectedUsers) {
                    stringResource(Res.string.selected_customers)
                } else {
                    stringResource(Res.string.selected_series)
                }

                StaticSelectedItemsPanel(
                    title = title,
                    items = viewState.itemIds,
                )
            }

            TextFields.Outlined(
                value = viewState.title,
                onValueChange = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeTitle(it)) },
                label = stringResource(resource = Res.string.title),
            )
            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.text,
                onValueChange = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeText(it)) },
                label = stringResource(resource = Res.string.text),
                minLines = 8,
            )

            DatePickers.DatePickerWithTimer(
                label = stringResource(resource = Res.string.expiration_date),
                value = viewState.expirationDateTime,
                onValueChange = {
                    onAction(
                        _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeExpirationDateTime(
                            it
                        )
                    )
                },
            )

            TextFields.Outlined(
                value = viewState.link,
                onValueChange = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.ChangeLink(it)) },
                label = stringResource(resource = Res.string.link),
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.send_notification),
                onClick = { onAction(_root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewAction.SendNotification) }
            )
        }
    }

    @Composable
    fun StaticSelectedItemsPanel(
        title: String,
        items: List<String>,
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
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(text = items.size.toString(), style = MaterialTheme.typography.labelMedium)
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
                Text(text, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationEditScreenContentPreview() {
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen()
        .PreviewContent(
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewState()
    )
}
// endregion