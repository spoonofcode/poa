package com.spoonofcode.poa.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.compose.Buttons
import com.spoonofcode.poa.core.ui.compose.LoopingVideo
import com.spoonofcode.poa.core.ui.compose.Spacers
import com.spoonofcode.poa.core.ui.compose.TextFields
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.delete
import com.spoonofcode.poa.resources.edit
import com.spoonofcode.poa.resources.gen_1_series
import com.spoonofcode.poa.resources.message_text
import com.spoonofcode.poa.resources.message_title
import com.spoonofcode.poa.resources.nft_info
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class ChatScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.gen_1_series,
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ChatViewModel, ChatViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ChatViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ChatViewModel,
        viewState: ChatViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
//            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
            onMessageTextChange = { viewModel.onMessageTextChange(it) },
            onMessageTitleChange = { viewModel.onMessageTitleChange(it) },
            sendToUser = { viewModel.sendToUser() },
            sendToTopics = { viewModel.sendToTopics() },
            onRemoteTokenChange = { viewModel.onRemoteTokenChange(it) },
            getFirebaseMessageToken = { viewModel.getFirebaseMessageToken() },
            clickOnTopicToSend = { viewModel.clickOnTopicToSend(it) },
            clickOnTopicToSubscribe = { viewModel.clickOnTopicToSubscribe(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ChatViewState,
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
//            Buttons.PrimaryButton(
//                text = "Get user token",
//                onClick = getFirebaseMessageToken
//            )
//
//            Texts.HSB("Firebase user message token: ${viewState.firebaseMessageToken}")
//
//            Spacers.VerticalBetweenFields()
//
//            TextFields.Outlined(
//                value = viewState.remoteToken,
//                onValueChange = onRemoteTokenChange,
//                label = stringResource(resource = Res.string.remote_user_token),
//            )
//
//            Spacers.VerticalBetweenFields()

            val nftAnimation = "https://raw.seadn.io/files/2a839046d7178aea08c51551c82d9f5e.mp4"
            NFTAnimation(nftAnimation)

            Spacers.VerticalBetweenFields()

            Texts.HSB("Send the information to users who have the Gen 1 Series.")

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.messageTitle,
                onValueChange = onMessageTitleChange,
                label = stringResource(resource = Res.string.message_title),
            )

            Spacers.VerticalBetweenFields()

            TextFields.OutlinedLong(
                value = viewState.messageText,
                onValueChange = onMessageTextChange,
                label = stringResource(resource = Res.string.message_text),
            )

//            Spacers.VerticalBetweenFields()
//
//            Buttons.PrimaryButton(
//                text = "Send to user",
//                onClick = sendToUser
//            )
//            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send notificaiton",
                onClick = sendToTopics
            )

//            Chips.SelectableChipsGroup(
//                label = "Selected topics to send",
//                options = viewState.topics,
//                selectedOptions = viewState.selectedTopicsToSend,
//                onOptionClick = clickOnTopicToSend,
//            )
//
//            Chips.SelectableChipsGroup(
//                label = "Selected topics to subscribe",
//                options = viewState.topics,
//                selectedOptions = viewState.selectedTopicsToSubscribe,
//                onOptionClick = clickOnTopicToSubscribe,
//            )
        }
    }

    @Composable
    fun NFTAnimation(nftAnimation: String) {
        Box {
            LoopingVideo(
                url = nftAnimation,
                modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
                mute = true      // silent background animation
            )
        }
    }

//    @Preview
//    @Composable
//    private fun ChatScreenPreview() {
//        ContentView(
//            snackbarHostState = remember { SnackbarHostState() },
//            content = ContentView(
//                viewState = ChatViewState(),
//            )
//        )
//    }
}