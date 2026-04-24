package com.spoonofcode.feature.notification.data.local

import com.russhwolf.settings.Settings
import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class LocalNotificationDataSource(
    private val settings: Settings,
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
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

    suspend fun getNotificationSettings(): NotificationSettings {
        return withContext(dispatcherProvider.io()) {
            NotificationSettings(
                isNotificationsPersonalizedEnabled = ksafe.get(
                    Keys.Notifications.IS_PERSONALIZED,
                    false
                ),
                isNotificationsFromPartnersEnabled = ksafe.get(
                    Keys.Notifications.IS_PARTNERS_ENABLED,
                    false
                ),
                isNotificationsMyProductSeriesEnabled = ksafe.get(
                    Keys.Notifications.IS_PRODUCT_SERIES_ENABLED,
                    false
                )
            )
        }
    }

    suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PERSONALIZED, notificationsPersonalizedEnabled)
            ksafe.put(Keys.Notifications.IS_PARTNERS_ENABLED, notificationsFromPartnersEnabled)
            ksafe.put(
                Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, notificationsMyProductSeriesEnabled
            )
        }
    }

    private companion object {
        const val READ_NOTIFICATIONS_KEY = "read_notifications_ids"
    }

    private object Keys {
        private const val BASE_PREFIX = "com.spoonofcode"

        object Notifications {
            private const val PREFIX = "$BASE_PREFIX.settings.notifications"
            const val IS_PERSONALIZED = "$PREFIX.personalized"
            const val IS_PRODUCT_SERIES_ENABLED = "$PREFIX.product_series"
            const val IS_PARTNERS_ENABLED = "$PREFIX.partners"
        }
    }
}