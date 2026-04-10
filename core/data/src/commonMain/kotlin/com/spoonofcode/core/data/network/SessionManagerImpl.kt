package com.spoonofcode.core.data.network

import com.russhwolf.settings.Settings

class SessionManagerImpl(private val settings: Settings) : SessionManager {
    override fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override fun clearSession() {
        clearSessionAccessToken()
        clearSessionRefreshToken()
        clearSessionUserId()
        clearSettingsNotificationFromAllPartners()
        clearSettingsNotificationFromMyPartners()
    }

    override fun enableAllNotifications() {
        saveSettingsPersonalizedPartnerNotifications(true)
        saveSettingsProductSeriesNotifications(true)
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

    override fun getSettingsPersonalizedPartnerNotifications(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_PERSONALIZED_PARTNER_NOTIFICATIONS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override fun saveSettingsPersonalizedPartnerNotifications(enable: Boolean) {
        settings.putBoolean(SETTINGS_PERSONALIZED_PARTNER_NOTIFICATIONS, enable)
    }

    override fun getSettingsProductSeriesNotifications(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_PRODUCT_SERIES_NOTIFICATIONS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override fun saveSettingsProductSeriesNotifications(enable: Boolean) {
        settings.putBoolean(SETTINGS_PRODUCT_SERIES_NOTIFICATIONS, enable)
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

    private fun clearSettingsNotificationFromAllPartners() {
        settings.remove(SETTINGS_PERSONALIZED_PARTNER_NOTIFICATIONS)
    }

    private fun clearSettingsNotificationFromMyPartners() {
        settings.remove(SETTINGS_PRODUCT_SERIES_NOTIFICATIONS)
    }

    companion object Companion {
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        private const val SESSION_USER_ID = "session_user_id"
        private const val SESSION_USER_EMAIL = "session_user_email"
        private const val SETTINGS_PERSONALIZED_PARTNER_NOTIFICATIONS =
            "settings_personalized_partner_notifications"
        private const val SETTINGS_PRODUCT_SERIES_NOTIFICATIONS =
            "settings_product_series_notifications"
    }
}