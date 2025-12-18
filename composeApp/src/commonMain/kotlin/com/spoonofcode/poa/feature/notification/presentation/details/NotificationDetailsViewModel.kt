package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch

internal class NotificationDetailsViewModel(
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
) : BaseViewModel<com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState, com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewAction>(
    _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState()
) {

    override fun onAction(action: com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewAction) {
        when (action) {
            is com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewAction.InitView -> initView(notificationId = action.notificationId)
        }
    }

    private fun initView(notificationId: String) {
        showLoadingView()
        viewModelScope.launch {
            getNotificationByIdUseCase(notificationId = notificationId)
                .onSuccess {
                    _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
                        _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState(
                            screenState = ScreenState.CONTENT,
                            notification = it
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun showLoadingView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        _root_ide_package_.com.spoonofcode.poa.core.ui.BaseViewModel.updateState {
            _root_ide_package_.com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewState(
                screenState = ScreenState.ERROR,
            )
        }
    }
}