package com.spoonofcode.poa.feature.notification.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.base.BaseViewModel
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsScreen
import kotlinx.coroutines.launch

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<NotificationOverviewViewState, NotificationOverviewViewAction>(
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
                    showContentView {
                        NotificationOverviewViewState(
                            notifications = it,
                        )
                    }
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