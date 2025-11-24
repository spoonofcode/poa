package com.spoonofcode.poa.feature.notification.overview

sealed interface NotificationOverviewViewAction {
    data object InitView : NotificationOverviewViewAction
    data class SelectNotification(val notificationId: String) : NotificationOverviewViewAction
}