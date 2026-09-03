package com.spoonofcode.feature.partner.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class PartnerEvent {
    data object PartnerUpdated : PartnerEvent()
}

class PartnerEventBus {
    private val _partnerUpdatedEvents = Channel<PartnerEvent>(Channel.BUFFERED)
    val partnerUpdatedEvents = _partnerUpdatedEvents.receiveAsFlow()

    fun emitPartnerUpdated() {
        _partnerUpdatedEvents.trySend(PartnerEvent.PartnerUpdated)
    }
}
