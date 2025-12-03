package com.spoonofcode.poa.feature.notification.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.notification.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationDetailsViewModel(
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
) : BaseViewModel<NotificationDetailsViewState, NotificationDetailsViewAction>(
    NotificationDetailsViewState()
) {

    override fun onAction(action: NotificationDetailsViewAction) {
        when (action) {
            is NotificationDetailsViewAction.InitView -> initView(notificationId = action.notificationId)
        }
    }

    private fun initView(notificationId: String) {
        viewModelScope.launch {
            try {
                val notification = getNotificationByIdUseCase(notificationId = notificationId)
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        notification = notification
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }
}