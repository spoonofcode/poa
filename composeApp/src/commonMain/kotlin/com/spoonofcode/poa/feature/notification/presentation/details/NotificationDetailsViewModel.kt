package com.spoonofcode.poa.feature.notification.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import kotlinx.coroutines.launch

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
        showLoadingView()
        viewModelScope.launch {
            getNotificationByIdUseCase(notificationId = notificationId)
                .onSuccess {
                    updateState {
                        NotificationDetailsViewState(
                            notification = it
                        )
                    }
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}