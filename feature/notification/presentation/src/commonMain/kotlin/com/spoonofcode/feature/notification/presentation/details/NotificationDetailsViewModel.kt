package com.spoonofcode.feature.notification.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.feature.notification.domain.usecase.MarkNotificationAsReadUseCase
import kotlinx.coroutines.launch

internal class NotificationDetailsViewModel(
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
    private val markNotificationAsReadUseCase: MarkNotificationAsReadUseCase,
) : BaseViewModel<NotificationDetailsViewState, NotificationDetailsViewAction, Nothing>(
    initialViewState = ViewState.Content(NotificationDetailsViewState()),
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
                    setContentState(
                        NotificationDetailsViewState(
                            notification = it
                        )
                    )
                    markNotificationAsReadUseCase(notificationId = notificationId)
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}
