package com.spoonofcode.poa.feature.notification.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsScreen
import kotlinx.coroutines.launch

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState, com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction>(
    NotificationOverviewViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction) {
        when (action) {
            NotificationOverviewViewAction.InitView -> initView()
            is com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction.SelectNotification -> selectNotification(
                notificationId = action.notificationId
            )
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getNotificationsUseCase()
                .onSuccess {
                    updateState {
                        NotificationOverviewViewState(
                            notifications = it,
                        )
                    }
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun selectNotification(notificationId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationDetailsScreen(
                    notificationId = notificationId
                )
            )
        }
    }
}