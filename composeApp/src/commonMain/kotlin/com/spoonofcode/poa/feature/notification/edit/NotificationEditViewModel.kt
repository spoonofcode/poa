package com.spoonofcode.poa.feature.notification.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.notification.SendNotificationUseCase
import com.spoonofcode.poa.core.model.NotificationRequest
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationEditViewModel(
    private val sendNotificationUseCase: SendNotificationUseCase,
) : BaseViewModel<NotificationEditViewState, NotificationEditViewAction>(NotificationEditViewState()) {

    override fun onAction(action: NotificationEditViewAction) {
        when (action) {
            is NotificationEditViewAction.ChangeExpirationDateTime -> changeExpirationDateTime(
                action.startDateTime
            )

            is NotificationEditViewAction.ChangeLink -> changeLink(action.link)
            is NotificationEditViewAction.ChangeText -> changeText(action.text)
            is NotificationEditViewAction.ChangeTitle -> changeTitle(action.title)
            is NotificationEditViewAction.InitView -> initView(action.itemIds)
            NotificationEditViewAction.SendNotification -> sendNotification()
        }
    }

    private fun initView(itemIds: List<String>) {
        updateState {
            copy(itemIds = itemIds)
        }
    }

    private fun changeTitle(title: String) {
        updateState {
            copy(title = title)
        }
    }

    private fun changeText(text: String) {
        updateState {
            copy(text = text)
        }
    }

    private fun changeExpirationDateTime(startDateTime: LocalDateTime) {
        updateState {
            copy(expirationDateTime = startDateTime)
        }
    }

    private fun changeLink(link: String) {
        updateState {
            copy(link = link)
        }
    }

    private fun sendNotification() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                sendNotificationUseCase(
                    notification = NotificationRequest(
                        title = currentState.title,
                        text = currentState.text,
                        link = currentState.link,
                        topics = currentState.itemIds,
                        expirationDateTime = currentState.expirationDateTime
                    )
                )
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                screenState = ScreenState.CONTENT
            )
        }
    }
}