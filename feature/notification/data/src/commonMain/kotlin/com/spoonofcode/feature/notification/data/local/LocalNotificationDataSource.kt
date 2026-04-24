package com.spoonofcode.feature.notification.data.local

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.KSafeWriteMode
import kotlinx.coroutines.withContext

class LocalNotificationDataSource(
    private val kSafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun markAsRead(notificationId: String) {
        val currentRead = kSafe.get<Set<String>>(Keys.READ_IDS, emptySet())

        if (!currentRead.contains(notificationId)) {
            val updatedRead = currentRead + notificationId

            kSafe.put(
                key = Keys.READ_IDS,
                value = updatedRead,
                mode = KSafeWriteMode.Plain,
            )
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
                    key = Keys.Settings.PERSONALIZED_ENABLED,
                    defaultValue = false
                ),
                isNotificationsFromPartnersEnabled = kSafe.get(
                    key = Keys.Settings.PARTNERS_ENABLED,
                    defaultValue = false
                ),
                isNotificationsMyProductSeriesEnabled = kSafe.get(
                    key = Keys.Settings.PRODUCT_SERIES_ENABLED,
                    defaultValue = false
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
            kSafe.put(
                key = Keys.Settings.PERSONALIZED_ENABLED,
                value = notificationsPersonalizedEnabled,
                mode = KSafeWriteMode.Plain,
            )
            kSafe.put(
                key = Keys.Settings.PARTNERS_ENABLED,
                value = notificationsFromPartnersEnabled,
                mode = KSafeWriteMode.Plain,
            )
            kSafe.put(
                key = Keys.Settings.PRODUCT_SERIES_ENABLED,
                value = notificationsMyProductSeriesEnabled,
                mode = KSafeWriteMode.Plain,
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