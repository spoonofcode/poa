package com.spoonofcode.feature.notification.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.core.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

internal class NotificationDetailsViewModel(
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
) : BaseViewModel<com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewState, com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewAction>(
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewState()
) {

    override fun onAction(action: com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewAction) {
        when (action) {
            is com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewAction.InitView -> initView(notificationId = action.notificationId)
        }
    }

    private fun initView(notificationId: String) {
        showLoadingView()
        viewModelScope.launch {
            getNotificationByIdUseCase(notificationId = notificationId)
                .onSuccess {
                    showContentView {
                        _root_ide_package_.com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewState(
                            notification = it
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}