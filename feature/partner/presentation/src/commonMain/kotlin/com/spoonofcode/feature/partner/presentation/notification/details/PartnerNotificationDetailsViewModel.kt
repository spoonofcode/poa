package com.spoonofcode.feature.partner.presentation.notification.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.feature.partner.domain.usecase.DeletePartnerNotificationUseCase
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerNotificationUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationDetailsViewModel(
    private val getPartnerNotificationUseCase: GetPartnerNotificationUseCase,
    private val deletePartnerNotificationUseCase: DeletePartnerNotificationUseCase,
) : BaseViewModel<PartnerNotificationDetailsViewState, PartnerNotificationDetailsViewAction>(
    PartnerNotificationDetailsViewState()
) {

    override fun onAction(action: PartnerNotificationDetailsViewAction) {
        when (action) {
            is PartnerNotificationDetailsViewAction.InitView -> initView(action.notificationId)
            PartnerNotificationDetailsViewAction.DeleteNotification -> deleteNotification()
            PartnerNotificationDetailsViewAction.EditNotification -> editNotificaiton()
        }
    }

    private fun initView(notificationId: String) {
        showLoadingView()
        viewModelScope.launch {
            getPartnerNotificationUseCase(notificationId = notificationId)
                .onSuccess {
                    showContentView {
                        PartnerNotificationDetailsViewState(
                            notification = it
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun deleteNotification() {
        viewModelScope.launch {
            deletePartnerNotificationUseCase.invoke(notificationId = currentState().notification!!.id)
        }
    }

    private fun editNotificaiton() {
        viewModelScope.launch {
//            viewModelNavigator.push(
//                routeResolver.resolve(NotificationModuleRoute.NotificationDetails(notificationId))
//            )
        }
    }
}
