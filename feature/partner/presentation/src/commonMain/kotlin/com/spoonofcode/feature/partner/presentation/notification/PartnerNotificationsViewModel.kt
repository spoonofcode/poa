package com.spoonofcode.feature.partner.presentation.notification

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationsUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationsViewModel(
    private val getPartnerNotificationsUseCase: GetPartnerNotificationsUseCase,
) :
    BaseViewModel<PartnerNotificationsViewState, PartnerNotificationsViewAction, Nothing>(
        initialViewState = ViewState.Content(PartnerNotificationsViewState()),
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
            getPartnerNotificationsUseCase()
                .onSuccess { partnerNotifications ->
                    setContentState(
                        PartnerNotificationsViewState(
                            partnerNotifications = partnerNotifications.filterNot { notification ->
                                notification.state == NOTIFICATION_CANCELED_STATE
                            },
                        )
                    )
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun selectNotification(notificationId: String) {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationDetails(
                    notificationId = notificationId
                )
            )
        }
    }

    companion object {
        private const val NOTIFICATION_CANCELED_STATE = "canceled"
    }
}