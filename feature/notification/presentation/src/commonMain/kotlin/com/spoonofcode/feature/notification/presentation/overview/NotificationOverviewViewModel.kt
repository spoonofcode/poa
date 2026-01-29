package com.spoonofcode.feature.notification.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import kotlinx.coroutines.launch

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewState, com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction>(
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewState()
) {

    override fun onAction(action: com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction.InitView -> initView()
            is com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewAction.SelectNotification -> selectNotification(
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
                        _root_ide_package_.com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewState(
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
                _root_ide_package_.com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen(
                    notificationId = notificationId
                )
            )
        }
    }
}