package com.spoonofcode.feature.notification.presentation.channel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModule
import kotlinx.coroutines.launch

internal class NotificationChannelViewModel :
    BaseViewModel<NotificationChannelViewState, NotificationChannelViewAction>(
        NotificationChannelViewState(),
        initialScreenState = ScreenState.CONTENT,
    ) {

    override fun onAction(action: NotificationChannelViewAction) {
        when (action) {
            NotificationChannelViewAction.SelectNotificationPush -> selectNotificationPush()
        }
    }

    private fun selectNotificationPush() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModule.NotificationSettingsScreen()
                )
            )
        }
    }
}