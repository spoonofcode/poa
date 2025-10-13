package com.spoonofcode.poa.feature.notification.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.DatePickers
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
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
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationEditScreen(
    val onlyToSelectedUsers: Boolean = false,
    val itemIds: List<String>,
) : BaseScreen<NotificationEditViewModel, NotificationEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    override fun provideInitAction(viewModel: NotificationEditViewModel?): () -> Unit =
        { viewModel?.initView(itemIds = itemIds) }

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationEditViewModel?,
        viewState: NotificationEditViewState
    ): @Composable ColumnScope.() -> Unit = ContentView(
        viewState = viewState,
        changeTitle = { viewModel?.changeTitle(it) },
        changeText = { viewModel?.changeText(it) },
        changeExpirationDateTime = { viewModel?.changeExpirationDateTime(it) },
        changeLink = { viewModel?.changeLink(it) },
        sendNotification = { viewModel?.sendNotification() },
    )

    @Composable
    private fun ContentView(
        viewState: NotificationEditViewState,
        changeTitle: (String) -> Unit,
        changeText: (String) -> Unit,
        changeExpirationDateTime: (LocalDateTime) -> Unit,
        changeLink: (String) -> Unit,
        sendNotification: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            if (viewState.itemIds.isNotEmpty()) {
                if (onlyToSelectedUsers) {
                    Texts.BLB(stringResource(Res.string.selected_customers))
                } else {
                    Texts.BLB(stringResource(Res.string.selected_series))
                }
                viewState.itemIds.forEach { itemId ->
                    Texts.BLB(text = itemId)
                }
            }

            TextFields.Outlined(
                value = viewState.title,
                onValueChange = { changeTitle(it) },
                label = stringResource(resource = Res.string.title),
            )
            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.text,
                onValueChange = { changeText(it) },
                label = stringResource(resource = Res.string.text),
                minLines = 8,
            )

            DatePickers.DatePickerWithTimer(
                label = stringResource(resource = Res.string.expiration_date),
                value = viewState.expirationDateTime,
                onValueChange = {
                    changeExpirationDateTime(it)
                },
            )

            TextFields.Outlined(
                value = viewState.link,
                onValueChange = { changeLink(it) },
                label = stringResource(resource = Res.string.link),
            )

            Spacers.Weight1(this)

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.send_notification),
                onClick = { sendNotification() }
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun NotificationEditScreenContentPreview() {
    NotificationEditScreen(itemIds = emptyList()).PreviewContent(
        NotificationEditViewState()
    )
}
// endregion