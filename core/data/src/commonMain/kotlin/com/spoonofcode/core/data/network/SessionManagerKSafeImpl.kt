package com.spoonofcode.core.data.network

import com.spoonofcode.core.data.coroutines.DispatcherProvider
import eu.anifantakis.lib.ksafe.KSafe
import kotlinx.coroutines.withContext

class SessionManagerKSafeImpl(
    private val ksafe: KSafe,
    private val dispatcherProvider: DispatcherProvider
) : SessionManager {

    // TODO zrobić jedna metode init session i tam ustawiac i zapisywac caly obiekt sessii

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun clearSession() {
        withContext(dispatcherProvider.io()) {
            clearSessionAccessToken()
            clearSessionRefreshToken()
            clearSessionUserId()
            clearSessionUserEmail()
            clearNotificationsPersonalizedksafe()
            clearNotificationsMyProductSeriesksafe()
            clearNotificationsFromPartnersksafe()
        }
    }

    override suspend fun disableAllNotifications() {
        withContext(dispatcherProvider.io()) {
            saveNotificationsPersonalizedSettings(false)
            saveNotificationsMyProductSeriesSettings(false)
            saveNotificationsFromPartnersSettings(false)
        }
    }

    override suspend fun saveSessionAccessToken(token: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(SESSION_ACCESS_TOKEN, token)
        }
    }

    override suspend fun getSessionAccessToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get(SESSION_ACCESS_TOKEN, null)
        }

    override suspend fun saveSessionRefreshToken(token: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(SESSION_REFRESH_TOKEN, token)
        }
    }

    override suspend fun getSessionRefreshToken(): String? =
        withContext(dispatcherProvider.io()) {
            ksafe.get(SESSION_REFRESH_TOKEN, null)
        }

    override suspend fun saveSessionUserId(userId: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(SESSION_USER_ID, userId)
        }
    }

    override suspend fun getSessionUserId(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get(SESSION_USER_ID, null)
            ) { "User ID cannot be null" }
        }

    override suspend fun saveSessionUserEmail(email: String) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(SESSION_USER_EMAIL, email)
        }
    }

    override suspend fun getSessionUserEmail(): String =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get(SESSION_USER_EMAIL, null)
            ) { "User email cannot be null" }
        }

    override suspend fun getNotificationsPersonalizedSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get(NOTIFICATIONS_PERSONALIZED_SETTINGS, null)
            ) { "NOTIFICATIONS_PERSONALIZED_SETTINGS cannot be null" }
        }

    override suspend fun saveNotificationsPersonalizedSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(NOTIFICATIONS_PERSONALIZED_SETTINGS, enable)
        }
    }

    override suspend fun getNotificationsMyProductSeriesSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS, null)
            ) { "NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS cannot be null" }
        }

    override suspend fun saveNotificationsMyProductSeriesSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS, enable)
        }
    }

    override suspend fun getNotificationsFromPartnersSettings(): Boolean =
        withContext(dispatcherProvider.io()) {
            requireNotNull(
                ksafe.get(NOTIFICATIONS_FROM_PARTNERS_SETTINGS, null)
            ) { "NOTIFICATIONS_FROM_PARTNERS_SETTINGS cannot be null" }
        }

    override suspend fun saveNotificationsFromPartnersSettings(enable: Boolean) {
        withContext(dispatcherProvider.io()) {
            ksafe.put(NOTIFICATIONS_FROM_PARTNERS_SETTINGS, enable)
        }
    }

    private suspend fun clearSessionAccessToken() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(SESSION_ACCESS_TOKEN)
        }
    }

    private suspend fun clearSessionRefreshToken() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(SESSION_REFRESH_TOKEN)

        }
    }

    private suspend fun clearSessionUserId() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(SESSION_USER_ID)

        }
    }

    private suspend fun clearSessionUserEmail() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(SESSION_USER_EMAIL)
        }
    }

    private suspend fun clearNotificationsPersonalizedksafe() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(NOTIFICATIONS_PERSONALIZED_SETTINGS)
        }
    }

    private suspend fun clearNotificationsMyProductSeriesksafe() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS)
        }
    }

    private suspend fun clearNotificationsFromPartnersksafe() {
        withContext(dispatcherProvider.io()) {
            ksafe.delete(NOTIFICATIONS_FROM_PARTNERS_SETTINGS)
        }
    }

    companion object Companion {
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        private const val SESSION_USER_ID = "session_user_id"
        private const val SESSION_USER_EMAIL = "session_user_email"
        private const val NOTIFICATIONS_PERSONALIZED_SETTINGS =
            "notifications_personalized_settings"
        private const val NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS =
            "notifications_my_product_series_settings"
        private const val NOTIFICATIONS_FROM_PARTNERS_SETTINGS =
            "notifications_from_partners_settings"
    }
}