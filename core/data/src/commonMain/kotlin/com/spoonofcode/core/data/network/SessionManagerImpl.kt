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
        saveSettingsNotificationFromAllPartners(true)
        saveSettingsNotificationFromMyPartners(true)
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

    override fun getSettingsNotificationFromAllPartners(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override fun saveSettingsNotificationFromAllPartners(enable: Boolean) {
        settings.putBoolean(SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS, enable)
    }

    override fun getSettingsNotificationFromMyPartners(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_NOTIFICATION_FROM_MY_PARTNERS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    override fun saveSettingsNotificationFromMyPartners(enable: Boolean) {
        settings.putBoolean(SETTINGS_NOTIFICATION_FROM_MY_PARTNERS, enable)
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
        settings.remove(SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS)
    }

    private fun clearSettingsNotificationFromMyPartners() {
        settings.remove(SETTINGS_NOTIFICATION_FROM_MY_PARTNERS)
    }

    companion object Companion {
        private const val KTOR_SESSION_ACCESS_TOKEN = "ktor_session_access_token"
        private const val KTOR_SESSION_REFRESH_TOKEN = "ktor_session_refresh_token"
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        private const val SESSION_USER_ID = "session_user_id"
        private const val SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS =
            "settings_notification_from_all_partners"
        private const val SETTINGS_NOTIFICATION_FROM_MY_PARTNERS =
            "settings_notification_from_my_partners"
    }
}