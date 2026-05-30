package com.spoonofcode.feature.notification.presentation.partner

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.notification.domain.usecase.GetPartnerNotificationTopicUseCase
import kotlinx.coroutines.launch

internal class PartnerNotificationsViewModel(
    private val getPartnerNotificationTopicUseCase: GetPartnerNotificationTopicUseCase,
) :
    BaseViewModel<PartnerNotificationsViewState, PartnerNotificationsViewAction>(
        PartnerNotificationsViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: PartnerNotificationsViewAction) {
        when (action) {
            PartnerNotificationsViewAction.SendNotificationToAllCustomers -> sendNotificationToAllCustomers()
        }
    }

    private fun sendNotificationToAllCustomers() {
        viewModelScope.launch {
        }
    }
}