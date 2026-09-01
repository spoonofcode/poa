package com.spoonofcode.feature.product.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class ProductEvent {
    data object ProductAdded : ProductEvent()
    data object ProductDeleted : ProductEvent()
    data object ProductUpdated : ProductEvent()
}

internal class ProductEventBus {
    private val _events = MutableSharedFlow<ProductEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    fun tryEmit(event: ProductEvent) {
        _events.tryEmit(event)
    }
}
