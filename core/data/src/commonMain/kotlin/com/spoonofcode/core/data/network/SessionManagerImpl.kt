package com.spoonofcode.core.data.network

import com.russhwolf.settings.Settings

class SessionManagerImpl(private val settings: Settings) : SessionManager {
    override fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override fun clearSession() {
        clearSessionAccessToken()
        clearSessionRefreshToken()
        clearSessionUserId()
        clearSessionUserEmail()
        clearNotificationsPersonalizedSettings()
        clearNotificationsMyProductSeriesSettings()
        clearNotificationsFromPartnersSettings()
    }

    override fun disableAllNotifications() {
        saveNotificationsPersonalizedSettings(false)
        saveNotificationsMyProductSeriesSettings(false)
        saveNotificationsFromPartnersSettings(false)
    }

    override fun saveSessionAccessToken(token: String) {
        settings.putString(SESSION_ACCESS_TOKEN, token)
    }

    override fun getSessionAccessToken(): String? = settings.getStringOrNull(SESSION_ACCESS_TOKEN)

    override fun saveSessionRefreshToken(token: String) {
        settings.putString(SESSION_REFRESH_TOKEN, token)
    }

    override fun getSessionRefreshToken(): String? = settings.getStringOrNull(SESSION_REFRESH_TOKEN)

    override fun saveSessionUserId(userId: String) {
        settings.putString(SESSION_USER_ID, userId)
    }

    override fun getSessionUserId(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_ID)
    ) { "User ID cannot be null" }

    override fun saveSessionUserEmail(email: String) {
        settings.putString(SESSION_USER_EMAIL, email)
    }

    override fun getSessionUserEmail(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_EMAIL)
    ) { "User email cannot be null" }

    override fun getNotificationsPersonalizedSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_PERSONALIZED_SETTINGS)
    ) { "NOTIFICATIONS_PERSONALIZED_SETTINGS cannot be null" }

    override fun saveNotificationsPersonalizedSettings(enable: Boolean) {
        settings.putBoolean(NOTIFICATIONS_PERSONALIZED_SETTINGS, enable)
    }

    override fun getNotificationsMyProductSeriesSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS)
    ) { "NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS cannot be null" }

    override fun saveNotificationsMyProductSeriesSettings(enable: Boolean) {
        settings.putBoolean(NOTIFICATIONS_MY_PRODUCT_SERIES_SETTINGS, enable)
    }

    override fun getNotificationsFromPartnersSettings(): Boolean = requireNotNull(
        settings.getBooleanOrNull(NOTIFICATIONS_FROM_PARTNERS_SETTINGS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override fun saveNotificationsFromPartnersSettings(enable: Boolean) {
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