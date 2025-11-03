package com.spoonofcode.poa.feature.notification.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.notification.GetNotificationsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.details.NotificationDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<NotificationOverviewViewState>(NotificationOverviewViewState()) {
    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val allNotifications = getNotificationsUseCase()
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        notifications = allNotifications,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun selectNotification(notificationId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationDetailsScreen(notificationId = notificationId))
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
}