package com.spoonofcode.core.data.network

import com.russhwolf.settings.Settings

class SessionManagerImpl(private val settings: Settings) : SessionManager {
    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun clearSession() {
        clearSessionAccessToken()
        clearSessionRefreshToken()
        clearSessionUserId()
        clearSessionUserEmail()
        clearNotificationsPersonalizedSettings()
        clearNotificationsMyProductSeriesSettings()
        clearNotificationsFromPartnersSettings()
    }

    override suspend fun disableAllNotifications() {
        saveNotificationsPersonalizedSettings(false)
        saveNotificationsMyProductSeriesSettings(false)
        saveNotificationsFromPartnersSettings(false)
    }

    override suspend fun saveSessionAccessToken(token: String) {
        settings.putString(SESSION_ACCESS_TOKEN, token)
    }

    override suspend fun getSessionAccessToken(): String? = settings.getStringOrNull(SESSION_ACCESS_TOKEN)

    override suspend fun saveSessionRefreshToken(token: String) {
        settings.putString(SESSION_REFRESH_TOKEN, token)
    }

    override suspend fun getSessionRefreshToken(): String? = settings.getStringOrNull(SESSION_REFRESH_TOKEN)

    override suspend fun saveSessionUserId(userId: String) {
        settings.putString(SESSION_USER_ID, userId)
    }

    override suspend fun getSessionUserId(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_ID)
    ) { "User ID cannot be null" }

    override suspend fun saveSessionUserEmail(email: String) {
        settings.putString(SESSION_USER_EMAIL, email)
    }

    override suspend fun getSessionUserEmail(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_EMAIL)
    ) { "User email cannot be null" }

    override suspend fun getNotificationsPersonalizedSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_PERSONALIZED_SETTINGS)
    ) { "NOTIFICATIONS_PERSONALIZED_SETTINGS cannot be null" }

    override suspend fun saveNotificationsPersonalizedSettings(enable: Boolean) {
        settings.putBoolean(NOTIFICATIONS_PERSONALIZED_SETTINGS, enable)
    }

    override suspend fun getNotificationsMyProductSeriesSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS)
    ) { "NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS cannot be null" }

    override suspend fun saveNotificationsMyProductSeriesSettings(enable: Boolean) {
        settings.putBoolean(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS, enable)
    }

    override suspend fun getNotificationsFromPartnersSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_FROM_PARTNERS_SETTINGS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override suspend fun saveNotificationsFromPartnersSettings(enable: Boolean) {
        settings.putBoolean(NOTIFICATIONS_FROM_PARTNERS_SETTINGS, enable)
    }

    private fun clearSessionAccessToken() {
        settings.remove(SESSION_ACCESS_TOKEN)
    }

    private fun clearSessionRefreshToken() {
        settings.remove(SESSION_REFRESH_TOKEN)
    }

    private fun clearSessionUserId() {
        settings.remove(SESSION_USER_ID)
    }

    private fun clearSessionUserEmail() {
        settings.remove(SESSION_USER_EMAIL)
    }

    private fun clearNotificationsPersonalizedSettings() {
        settings.remove(NOTIFICATIONS_PERSONALIZED_SETTINGS)
    }

    private fun clearNotificationsMyProductSeriesSettings() {
        settings.remove(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS)
    }

    private fun clearNotificationsFromPartnersSettings() {
        settings.remove(NOTIFICATIONS_FROM_PARTNERS_SETTINGS)
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