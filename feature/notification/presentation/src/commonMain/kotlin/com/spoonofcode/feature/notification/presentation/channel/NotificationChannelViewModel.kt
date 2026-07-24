package com.spoonofcode.feature.notification.presentation.channel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import kotlinx.coroutines.launch

internal class NotificationChannelViewModel :
    BaseViewModel<NotificationChannelViewState, NotificationChannelViewAction, Nothing>(
        initialViewState = ViewState.Content(NotificationChannelViewState()),
    ) {

    override fun onAction(action: NotificationChannelViewAction) {
        when (action) {
            NotificationChannelViewAction.SelectNotificationPush -> selectNotificationPush()
        }
    }

    private fun selectNotificationPush() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationModuleRoute.NotificationSettings()
            )
        }
    }
}