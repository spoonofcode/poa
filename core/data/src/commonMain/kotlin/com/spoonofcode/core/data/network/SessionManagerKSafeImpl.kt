package com.spoonofcode.core.data.network

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class SessionManagerKSafeImpl(
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) : SessionManager {

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            ksafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
            ksafe.put(Keys.Session.USER_ID, userId)
        }
    }

    override suspend fun updateSessionTokens(
        accessToken: String,
        refreshToken: String
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.ACCESS_TOKEN, accessToken)
            ksafe.put(Keys.Session.REFRESH_TOKEN, refreshToken)
        }
    }

    override suspend fun clearSession() {
        withContext(dispatcherProvider.io()) {
            ksafe.clearAll()
        }
    }

    override suspend fun getSessionAccessToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.ACCESS_TOKEN, null)
        }

    override suspend fun getSessionRefreshToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.REFRESH_TOKEN, null)
        }

    override suspend fun getSessionUserId(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get<String?>(Keys.Session.USER_ID, null)
            ) { "User ID cannot be null" }
        }

    override suspend fun getNotificationSettings(): NotificationSettings {
        return  withContext(dispatcherProvider.io()) {
            NotificationSettings(
                isNotificationsPersonalizedEnabled = ksafe.get(Keys.Notifications.IS_PERSONALIZED, false),
                isNotificationsFromPartnersEnabled = ksafe.get(Keys.Notifications.IS_PARTNERS_ENABLED, false),
                isNotificationsMyProductSeriesEnabled = ksafe.get(Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, false)
            )

        }
    }

    override suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    ) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PERSONALIZED, notificationsPersonalizedEnabled)
            ksafe.put(Keys.Notifications.IS_PARTNERS_ENABLED, notificationsFromPartnersEnabled)
            ksafe.put(Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, notificationsMyProductSeriesEnabled)
        }
    }

    // --- KEY DEFINITIONS ---
    private object Keys {
        private const val BASE_PREFIX = "com.spoonofcode"

        object Session {
            private const val PREFIX = "$BASE_PREFIX.session"
            const val ACCESS_TOKEN = "$PREFIX.access_token"
            const val REFRESH_TOKEN = "$PREFIX.refresh_token"
            const val USER_ID = "$PREFIX.user_id"
        }

        object Notifications {
            private const val PREFIX = "$BASE_PREFIX.settings.notifications"
            const val IS_PERSONALIZED = "$PREFIX.personalized"
            const val IS_PRODUCT_SERIES_ENABLED = "$PREFIX.product_series"
            const val IS_PARTNERS_ENABLED = "$PREFIX.partners"
        }
    }
}