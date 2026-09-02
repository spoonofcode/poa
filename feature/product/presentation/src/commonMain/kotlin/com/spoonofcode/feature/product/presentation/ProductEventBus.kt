package com.spoonofcode.feature.product.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class ProductEvent {
    data object ProductAdded : ProductEvent()
    data object ProductDeleted : ProductEvent()
    data object ProductUpdated : ProductEvent()
}

internal class ProductEventBus {
    private val _events = Channel<ProductEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun tryEmit(event: ProductEvent) {
        _events.trySend(event)
    }
}
