package com.spoonofcode.feature.partner.presentation.notification

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationsUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationsViewModel(
    private val getPartnerNotificationsUseCase: GetPartnerNotificationsUseCase,
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
            getPartnerNotificationsUseCase()
                .onSuccess {
                    showContentView {
                        PartnerNotificationsViewState(
                            partnerNotifications = it,
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
                routeResolver.resolve(
                    PartnerModuleRoute.PartnerNotificationDetails(
                        notificationId = notificationId
                    )
                )
            )
        }
    }
}