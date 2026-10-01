package com.spoonofcode.poa.app

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object NotificationNavigationManager {
    private val _notificationClicks = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val notificationClicks: SharedFlow<String> = _notificationClicks.asSharedFlow()

    fun handleNotificationClick(notificationId: String) {
        _notificationClicks.tryEmit(notificationId)
    }
}
