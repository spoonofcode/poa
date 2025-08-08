package com.spoonofcode.poa.feature.notification.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.notification.GetAllNotificationsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.notification.details.NotificationDetailsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class NotificationOverviewViewModel(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
) : BaseViewModel<NotificationOverviewViewState>(NotificationOverviewViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val allNotifications = getAllNotificationsUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        initNotifications = allNotifications,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun selectNotification(notificationId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationDetailsScreen(notificationId = notificationId))
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
}