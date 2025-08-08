package com.spoonofcode.poa.feature.notification.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.app.MainHostScreen
import com.spoonofcode.poa.core.domain.notification.SendNotificationUseCase
import com.spoonofcode.poa.core.model.NotificationRequest
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationEditViewModel(
    private val sendNotificationUseCase: SendNotificationUseCase,
) : BaseViewModel<NotificationEditViewState>(NotificationEditViewState()) {

    init {
        initView()
    }

    fun initView() {
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
                sendNotificationUseCase(
                    notification = NotificationRequest(
                        title = viewState.value.title,
                        text = viewState.value.text,
                        link = viewState.value.link,
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
                isLoadingView = true,
                isErrorView = false,
            )
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

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}