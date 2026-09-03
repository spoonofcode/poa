package com.spoonofcode.feature.product.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class ProductEvent {
    data object ProductAdded : ProductEvent()
    data object ProductDeleted : ProductEvent()
    data object ProductUpdated : ProductEvent()
}

internal class ProductEventBus {
    private val _overviewEvents = Channel<ProductEvent>(Channel.BUFFERED)
    val overviewEvents = _overviewEvents.receiveAsFlow()

    private val _detailsEvents = Channel<ProductEvent>(Channel.BUFFERED)
    val detailsEvents = _detailsEvents.receiveAsFlow()

    fun emitOverview(event: ProductEvent) {
        _overviewEvents.trySend(event)
    }

    fun emitDetails(event: ProductEvent) {
        _detailsEvents.trySend(event)
    }
}
