package com.spoonofcode.feature.partner.presentation.notification

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationsViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) :
    BaseViewModel<PartnerNotificationsViewState, PartnerNotificationsViewAction>(
        PartnerNotificationsViewState(),
    ) {

    override fun onAction(action: PartnerNotificationsViewAction) {
        when (action) {
            PartnerNotificationsViewAction.InitView -> initView()
            is PartnerNotificationsViewAction.SelectNotification -> selectNotification(
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
                        PartnerNotificationsViewState(
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
                routeResolver.resolve(NotificationModuleRoute.NotificationDetails(notificationId))
            )
        }
    }
}