package com.spoonofcode.feature.partner.presentation.notification

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class PartnerNotificationEvent {
    data object PartnerNotificationAdded : PartnerNotificationEvent()
    data object PartnerNotificationDeleted : PartnerNotificationEvent()
    data object PartnerNotificationUpdated : PartnerNotificationEvent()
}

internal class PartnerNotificationEventBus {
    private val _events = Channel<PartnerNotificationEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun tryEmit(event: PartnerNotificationEvent) {
        _events.trySend(event)
    }
}
