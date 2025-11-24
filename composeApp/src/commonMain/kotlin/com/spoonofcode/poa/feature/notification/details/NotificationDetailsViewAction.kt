package com.spoonofcode.poa.feature.notification.details

sealed interface NotificationDetailsViewAction {
    data class InitView(val notificationId: String) : NotificationDetailsViewAction
}