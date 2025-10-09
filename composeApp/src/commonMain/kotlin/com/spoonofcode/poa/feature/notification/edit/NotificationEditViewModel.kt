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
) : BaseViewModel<NotificationEditViewState>(NotificationEditViewState()) {

    fun initView(itemIds: List<String>) {
        viewModelScope.launch {
            updateState {
                copy(itemIds = itemIds)
            }
        }
    }

    fun changeTitle(title: String) {
        viewModelScope.launch {
            updateState {
                copy(title = title)
            }
        }
    }

    fun changeText(text: String) {
        viewModelScope.launch {
            updateState {
                copy(text = text)
            }
        }
    }

    fun changeExpirationDateTime(startDateTime: LocalDateTime) {
        viewModelScope.launch {
            updateState {
                copy(expirationDateTime = startDateTime)
            }
        }
    }

    fun changeLink(link: String) {
        viewModelScope.launch {
            updateState {
                copy(link = link)
            }
        }
    }

    fun sendNotification() {
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

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
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