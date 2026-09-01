package com.spoonofcode.feature.partner.presentation.notification

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class PartnerNotificationEvent {
    data object PartnerNotificationAdded : PartnerNotificationEvent()
    data object PartnerNotificationDeleted : PartnerNotificationEvent()
    data object PartnerNotificationUpdated : PartnerNotificationEvent()
}

internal class PartnerNotificationEventBus {
    private val _events = MutableSharedFlow<PartnerNotificationEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    fun tryEmit(event: PartnerNotificationEvent) {
        _events.tryEmit(event)
    }
}
