package com.spoonofcode.feature.notification.presentation.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.feature.notification.domain.usecase.MarkNotificationAsReadUseCase
import kotlinx.coroutines.launch

internal class NotificationDetailsViewModel(
    private val notificationId: String,
    private val getNotificationByIdUseCase: GetNotificationByIdUseCase,
    private val markNotificationAsReadUseCase: MarkNotificationAsReadUseCase,
) : BaseViewModel<NotificationDetailsViewState, NotificationDetailsViewAction, Nothing>() {
    override val startAction = {
        initView(notificationId = notificationId)
    }

    override fun onAction(action: NotificationDetailsViewAction) {
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
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }
}
