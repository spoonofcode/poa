package com.spoonofcode.core.data.network

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class SessionManagerKSafeImpl(
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) : SessionManager {

    // --- SESSION ---

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun clearSession() {
        withContext(dispatcherProvider.io()) {
            ksafe.clearAll()
        }
    }

    override suspend fun saveSessionAccessToken(token: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.ACCESS_TOKEN, token)
        }
    }

    override suspend fun getSessionAccessToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.ACCESS_TOKEN, null)
        }

    override suspend fun saveSessionRefreshToken(token: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.REFRESH_TOKEN, token)
        }
    }

    override suspend fun getSessionRefreshToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get<String?>(Keys.Session.REFRESH_TOKEN, null)
        }

    override suspend fun saveSessionUserId(userId: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.USER_ID, userId)
        }
    }

    override suspend fun getSessionUserId(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get<String?>(Keys.Session.USER_ID, null)
            ) { "User ID cannot be null" }
        }

    override suspend fun saveSessionUserEmail(email: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Session.USER_EMAIL, email)
        }
    }

    override suspend fun getSessionUserEmail(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get<String?>(Keys.Session.USER_EMAIL, null)
            ) { "User email cannot be null" }
        }

    // --- NOTIFICATIONS ---

    override suspend fun getNotificationsPersonalizedSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            ksafe.get(Keys.Notifications.IS_PERSONALIZED, false)
        }

    override suspend fun saveNotificationsPersonalizedSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PERSONALIZED, enable)
        }
    }

    override suspend fun getNotificationsMyProductSeriesSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            ksafe.get(Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, false)
        }

    override suspend fun saveNotificationsMyProductSeriesSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PRODUCT_SERIES_ENABLED, enable)
        }
    }

    override suspend fun getNotificationsFromPartnersSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            ksafe.get(Keys.Notifications.IS_PARTNERS_ENABLED, false)
        }

    override suspend fun saveNotificationsFromPartnersSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(Keys.Notifications.IS_PARTNERS_ENABLED, enable)
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
            const val USER_EMAIL = "$PREFIX.user_email"
        }

        object Notifications {
            private const val PREFIX = "$BASE_PREFIX.settings.notifications"
            const val IS_PERSONALIZED = "$PREFIX.personalized"
            const val IS_PRODUCT_SERIES_ENABLED = "$PREFIX.product_series"
            const val IS_PARTNERS_ENABLED = "$PREFIX.partners"
        }
    }
}