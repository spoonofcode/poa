package com.spoonofcode.feature.product.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class ProductEvent {
    data object ProductAdded : ProductEvent()
    data object ProductDeleted : ProductEvent()
}

internal class ProductEventBus {
    private val _events = MutableSharedFlow<ProductEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    suspend fun emit(event: ProductEvent) {
        _events.emit(event)
    }

    fun tryEmit(event: ProductEvent) {
        _events.tryEmit(event)
    }
}
