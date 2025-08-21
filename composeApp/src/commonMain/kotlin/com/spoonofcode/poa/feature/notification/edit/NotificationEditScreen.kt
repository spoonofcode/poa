package com.spoonofcode.poa.feature.notification.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.link
import com.spoonofcode.poa.resources.notification
import com.spoonofcode.poa.resources.send_notification
import com.spoonofcode.poa.resources.text
import com.spoonofcode.poa.resources.title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class NotificationEditScreen(
    val seriesIds: List<String>,
) : BaseScreen<NotificationEditViewModel, NotificationEditViewState>() {

    override fun provideTopAppBarTitle() = Res.string.notification

    @Composable
    override fun provideViewModel() = koinViewModel<NotificationEditViewModel>()

    @Composable
    override fun provideContent(
        viewModel: NotificationEditViewModel?,
        viewState: NotificationEditViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel?.initView(seriesIds = seriesIds)
        }

        return ContentView(
            viewState = viewState,
            changeTitle = { viewModel?.changeTitle(it) },
            changeText = { viewModel?.changeText(it) },
            changeLink = { viewModel?.changeLink(it) },
            sendNotification = { viewModel?.sendNotification() },
        )
    }

    @Composable
    private fun ContentView(
        viewState: NotificationEditViewState,
        changeTitle: (String) -> Unit,
        changeText: (String) -> Unit,
        changeLink: (String) -> Unit,
        sendNotification: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {

            Texts.BLB("Selected Series ID: ${viewState.seriesIds}")

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
            )

            TextFields.Outlined(
                value = viewState.link,
                onValueChange = { changeLink(it) },
                label = stringResource(resource = Res.string.link),
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.send_notification),
                onClick = { sendNotification() }
            )
        }
    }

    // region previews
    @Preview
    @Composable
    fun InitializedHomeScreenPreview() {
        ContentView(
            viewState = NotificationEditViewState(),
            changeTitle = {},
            changeText = {},
            changeLink = {},
            sendNotification = {},
        )
    }
    // endregion
}

// region previews
@Preview
@Composable
private fun NotificationEditScreenContentPreview() {
    NotificationEditScreen(seriesIds = emptyList()).PreviewContent(
        NotificationEditViewState()
    )
}
// endregion