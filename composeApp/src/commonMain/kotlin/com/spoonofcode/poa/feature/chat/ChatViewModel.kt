package com.spoonofcode.poa.feature.chat

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.SendMessageFCMUseCase
import com.spoonofcode.poa.core.model.MessageFCM
import com.spoonofcode.poa.core.model.NotificationFCM
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.core.ui.utils.FirebasePushService
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class ChatViewModel(
    private val sendMessageFCMUseCase: SendMessageFCMUseCase,
    private val firebasePushService: FirebasePushService,
) : BaseViewModel<ChatViewState>(ChatViewState()) {

    fun onRemoteTokenChange(newToken: String) {
        updateState {
            copy(
                remoteToken = newToken,
            )
        }
    }

    fun onMessageTitleChange(messageTitle: String) {
        updateState {
            copy(
                messageTitle = messageTitle,
            )
        }
    }

    fun onMessageTextChange(messageText: String) {
        updateState {
            copy(
                messageText = messageText,
            )
        }
    }

    fun getFirebaseMessageToken() {
        viewModelScope.launch {
            val token = firebasePushService.getMessageToken()

            updateState {
                copy(
                    firebaseMessageToken = token,
                )
            }
        }
    }

    fun sendToUser() {
        viewModelScope.launch {
            val currentState = currentState()
            val messageDto = MessageFCM(
                tokens = listOf(currentState.remoteToken),
                notification = NotificationFCM(
                    title = currentState.messageTitle,
                    body = currentState.messageText,
                    imageUrl = currentState.imageUrl,
                )
            )
            try {
                sendMessageFCMUseCase(
                    messageDto = messageDto,
                )
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun sendToTopics() {
        viewModelScope.launch {
            val currentState = currentState()
            val messageDto = MessageFCM(
                topics = currentState.selectedTopicsToSend,
                notification = NotificationFCM(
                    title = currentState.messageTitle,
                    body = currentState.messageText,
                )
            )
            try {
                sendMessageFCMUseCase(
                    messageDto = messageDto,
                )
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun clickOnTopicToSend(topic: String) {
        val currentSelectedTopicsToSend = currentState().selectedTopicsToSend.toMutableList()
        if (topic in currentSelectedTopicsToSend) {
            currentSelectedTopicsToSend.remove(topic)
        } else {
            currentSelectedTopicsToSend.add(topic)
        }
        updateState {
            copy(
                selectedTopicsToSend = currentSelectedTopicsToSend.toList()
            )
        }
    }

    fun clickOnTopicToSubscribe(topic: String) {
        viewModelScope.launch {
            try {
                val currentSelectedTopicsToSubscribe =
                    currentState().selectedTopicsToSubscribe.toMutableList()
                if (topic in currentSelectedTopicsToSubscribe) {
                    currentSelectedTopicsToSubscribe.remove(topic)
                    firebasePushService.unsubscribeFromTopic(topic = topic)
                } else {
                    currentSelectedTopicsToSubscribe.add(topic)
                    firebasePushService.subscribeToTopic(topic = topic)
                }
                updateState {
                    copy(
                        selectedTopicsToSubscribe = currentSelectedTopicsToSubscribe.toList()
                    )
                }
            } catch (e: Exception) {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
            }

        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}