package com.spoonofcode.poa.feature.notification.presentation.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch

internal class NotificationOverviewViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState, com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction>(
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewAction.InitView -> initView()
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
                    _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
                        _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState(
                            screenState = ScreenState.CONTENT,
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
            _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.viewModelNavigator.push(
                _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsScreen(
                    notificationId = notificationId
                )
            )
        }
    }
    private fun showLoadingView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewState(
                screenState = ScreenState.ERROR,
            )
        }
    }
}