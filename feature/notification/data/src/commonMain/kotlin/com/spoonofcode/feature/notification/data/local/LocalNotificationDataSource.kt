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
        val currentRead = kSafe.get<Set<String>>(Keys.READ_IDS, emptySet())

        if (!currentRead.contains(notificationId)) {
            val updatedRead = currentRead + notificationId

            kSafe.put(Keys.READ_IDS, updatedRead)
        }
    }

    suspend fun isRead(notificationId: String): Boolean {
        return kSafe.get<Set<String>>(Keys.READ_IDS, emptySet())
            .contains(notificationId)
    }

    suspend fun getNotificationSettings(): NotificationSettings {
        return withContext(dispatcherProvider.io()) {
            NotificationSettings(
                isNotificationsPersonalizedEnabled = kSafe.get(
                    Keys.Settings.PERSONALIZED_ENABLED,
                    false
                ),
                isNotificationsFromPartnersEnabled = kSafe.get(
                    Keys.Settings.PARTNERS_ENABLED,
                    false
                ),
                isNotificationsMyProductSeriesEnabled = kSafe.get(
                    Keys.Settings.PRODUCT_SERIES_ENABLED,
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
            kSafe.put(Keys.Settings.PERSONALIZED_ENABLED, notificationsPersonalizedEnabled)
            kSafe.put(Keys.Settings.PARTNERS_ENABLED, notificationsFromPartnersEnabled)
            kSafe.put(
                Keys.Settings.PRODUCT_SERIES_ENABLED, notificationsMyProductSeriesEnabled
            )
        }
    }

    private object Keys {
        private const val PREFIX = "com.spoonofcode.feature.notification"

        const val READ_IDS = "$PREFIX.read_ids"

        object Settings {
            private const val SETTINGS_PREFIX = "$PREFIX.settings"
            const val PERSONALIZED_ENABLED = "$SETTINGS_PREFIX.personalized_enabled"
            const val PRODUCT_SERIES_ENABLED = "$SETTINGS_PREFIX.product_series_enabled"
            const val PARTNERS_ENABLED = "$SETTINGS_PREFIX.partners_enabled"
        }
    }
}