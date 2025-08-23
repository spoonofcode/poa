package com.spoonofcode.poa.feature.messagefcm

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.Chips
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.utils.LocalClipboardManager
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.copy_to_clipboard
import com.spoonofcode.poa.resources.get_user_token
import com.spoonofcode.poa.resources.nft_info
import com.spoonofcode.poa.resources.notification_body
import com.spoonofcode.poa.resources.notification_title
import com.spoonofcode.poa.resources.remote_user_token
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

internal class MessageFCMScreen() : BaseScreen<MessageFCMViewModel, MessageFCMViewState>() {

    override fun provideTopAppBarTitle() = Res.string.nft_info

    @Composable
    override fun provideViewModel() = koinViewModel<MessageFCMViewModel>()

    @Composable
    override fun provideContent(
        viewModel: MessageFCMViewModel?,
        viewState: MessageFCMViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
//            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
            onMessageTextChange = { viewModel?.onMessageTextChange(it) },
            onMessageTitleChange = { viewModel?.onMessageTitleChange(it) },
            sendToUser = { viewModel?.sendToUser() },
            sendToTopics = { viewModel?.sendToTopics() },
            onRemoteTokenChange = { viewModel?.onRemoteTokenChange(it) },
            getFirebaseMessageToken = { viewModel?.getFirebaseMessageToken() },
            clickOnTopicToSend = { viewModel?.clickOnTopicToSend(it) },
            clickOnTopicToSubscribe = { viewModel?.clickOnTopicToSubscribe(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: MessageFCMViewState,
        onMessageTextChange: (String) -> Unit,
        onMessageTitleChange: (String) -> Unit,
        sendToUser: () -> Unit,
        sendToTopics: () -> Unit,
        onRemoteTokenChange: (String) -> Unit,
        getFirebaseMessageToken: () -> Unit,
        clickOnTopicToSend: (String) -> Unit,
        clickOnTopicToSubscribe: (String) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.get_user_token),
                onClick = getFirebaseMessageToken
            )

            Texts.HSB("Firebase user message token: ${viewState.fcmUserToken}")

            val clipboardManager = LocalClipboardManager.current

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.copy_to_clipboard),
                onClick = { clipboardManager?.setText(viewState.fcmUserToken) }
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.fcmRemoteUserToken,
                onValueChange = onRemoteTokenChange,
                label = stringResource(resource = Res.string.remote_user_token),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.notificationTitle,
                onValueChange = onMessageTitleChange,
                label = stringResource(resource = Res.string.notification_title),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.notificationBody,
                onValueChange = onMessageTextChange,
                label = stringResource(resource = Res.string.notification_body),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to user",
                onClick = sendToUser
            )
            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to topics",
                onClick = sendToTopics
            )

            Chips.SelectableChipsGroup(
                label = "Selected topics to send",
                options = viewState.topics,
                selectedOptions = viewState.selectedTopicsToSend,
                onOptionClick = clickOnTopicToSend,
            )

            Chips.SelectableChipsGroup(
                label = "Selected topics to subscribe",
                options = viewState.topics,
                selectedOptions = viewState.selectedTopicsToSubscribe,
                onOptionClick = clickOnTopicToSubscribe,
            )
        }
    }
}

// region previews
@Preview
@Composable
private fun MessageFCMScreenContentPreview() {
    MessageFCMScreen().PreviewContent(
        MessageFCMViewState()
    )
}
// endregion