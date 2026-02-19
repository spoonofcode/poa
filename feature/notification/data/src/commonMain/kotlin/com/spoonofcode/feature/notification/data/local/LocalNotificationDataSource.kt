package com.spoonofcode.feature.notification.data.local

import com.russhwolf.settings.Settings

class LocalNotificationDataSource(
    private val settings: Settings,
) {
    fun markAsRead(notificationId: String) {
        val currentRead = settings.getString(READ_NOTIFICATIONS_KEY, "")
        if (!currentRead.contains(notificationId)) {
            val updatedRead =
                if (currentRead.isEmpty()) notificationId else "$currentRead,$notificationId"
            settings.putString(READ_NOTIFICATIONS_KEY, updatedRead)
        }
    }

    fun isRead(notificationId: String): Boolean {
        return settings.getString(READ_NOTIFICATIONS_KEY, "").contains(notificationId)
    }

    private companion object {
        const val READ_NOTIFICATIONS_KEY = "read_notifications_ids"
    }
}