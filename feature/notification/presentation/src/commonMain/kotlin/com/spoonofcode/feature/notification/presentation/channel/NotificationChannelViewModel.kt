package com.spoonofcode.feature.notification.presentation.channel

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.presentation.NotificationEvent
import com.spoonofcode.feature.notification.presentation.NotificationEventBus
import com.spoonofcode.feature.notification.presentation.Res
import com.spoonofcode.feature.notification.presentation.notification_settings_updated
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

internal class NotificationChannelViewModel(
    private val notificationEventBus: NotificationEventBus,
) :
    BaseViewModel<NotificationChannelViewState, NotificationChannelViewAction, Nothing>(
        initialViewState = ViewState.Content(NotificationChannelViewState()),
    ) {

    init {
        observeNotificationEvents()
    }

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

    private fun observeNotificationEvents() {
        viewModelScope.launch {
            notificationEventBus.overviewEvents.collect { event ->
                when (event) {
                    NotificationEvent.SettingsUpdated -> {
                        showSuccessSnackbar(getString(Res.string.notification_settings_updated))
                    }
                }
            }
        }
    }
}
