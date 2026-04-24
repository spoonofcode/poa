package com.spoonofcode.feature.notification.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class LocalNotificationDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun markAsRead(notificationId: String) {
        val currentRead = kSafe.get<Set<String>>(Keys.READ_NOTIFICATIONS_KEY, emptySet())

        if (!currentRead.contains(notificationId)) {
            val updatedRead = currentRead + notificationId

            kSafe.put(Keys.READ_NOTIFICATIONS_KEY, updatedRead)
        }
    }

    suspend fun isRead(notificationId: String): Boolean {
        return kSafe.get<Set<String>>(Keys.READ_NOTIFICATIONS_KEY, emptySet())
            .contains(notificationId)
    }

    suspend fun getNotificationSettings(): NotificationSettings {
        return withContext(dispatcherProvider.io()) {
            NotificationSettings(
                isNotificationsPersonalizedEnabled = kSafe.get(
                    Keys.Notifications.IS_PERSONALIZED,
                    false
                ),
                isNotificationsFromPartnersEnabled = kSafe.get(
                    Keys.Notifications.IS_PARTNERS_ENABLED,
                    false
                ),
                isNotificationsMyProductSeriesEnabled = kSafe.get(
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
            kSafe.put(Keys.Notifications.IS_PERSONALIZED, notificationsPersonalizedEnabled)
            kSafe.put(Keys.Notifications.IS_PARTNERS_ENABLED, notificationsFromPartnersEnabled)
            kSafe.put(
                Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, notificationsMyProductSeriesEnabled
            )
        }
    }

    private object Keys {
        private const val BASE_PREFIX = "com.spoonofcode"

        const val READ_NOTIFICATIONS_KEY = "read_notifications_ids"

        object Notifications {
            private const val PREFIX = "$BASE_PREFIX.settings.notifications"
            const val IS_PERSONALIZED = "$PREFIX.personalized"
            const val IS_PRODUCT_SERIES_ENABLED = "$PREFIX.product_series"
            const val IS_PARTNERS_ENABLED = "$PREFIX.partners"
        }
    }
}