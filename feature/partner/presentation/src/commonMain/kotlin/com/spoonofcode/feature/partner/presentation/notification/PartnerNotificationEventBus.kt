package com.spoonofcode.feature.partner.presentation.notification

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class PartnerNotificationEvent {
    data object PartnerNotificationAdded : PartnerNotificationEvent()
    data object PartnerNotificationDeleted : PartnerNotificationEvent()
    data object PartnerNotificationUpdated : PartnerNotificationEvent()
}

internal class PartnerNotificationEventBus {
    private val _overviewEvents = Channel<PartnerNotificationEvent>(Channel.BUFFERED)
    val overviewEvents = _overviewEvents.receiveAsFlow()

    private val _detailsEvents = Channel<PartnerNotificationEvent>(Channel.BUFFERED)
    val detailsEvents = _detailsEvents.receiveAsFlow()

    fun emitOverview(event: PartnerNotificationEvent) {
        _overviewEvents.trySend(event)
    }

    fun emitDetails(event: PartnerNotificationEvent) {
        _detailsEvents.trySend(event)
    }
}
