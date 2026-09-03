package com.spoonofcode.feature.notification.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class NotificationEvent {
    data object SettingsUpdated : NotificationEvent()
}

class NotificationEventBus {
    private val _overviewEvents = Channel<NotificationEvent>(Channel.BUFFERED)
    val overviewEvents = _overviewEvents.receiveAsFlow()

    fun emitOverview(event: NotificationEvent) {
        _overviewEvents.trySend(event)
    }
}
