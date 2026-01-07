package com.spoonofcode.poa.core.network

import com.russhwolf.settings.Settings
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class SessionManager(private val settings: Settings) {
    fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    fun clearSession() {
        clearSessionAccessToken()
        clearSessionRefreshToken()
        clearSessionUserId()
        clearSettingsNotificationFromAllPartners()
        clearSettingsNotificationFromMyPartners()
    }

    fun enableAllNotifications() {
        saveSettingsNotificationFromAllPartners(true)
        saveSettingsNotificationFromMyPartners(true)
    }

    // START KTOR SERVER SESSION
    fun saveKtorSessionAccessToken(token: String) {
        settings.putString(KTOR_SESSION_ACCESS_TOKEN, token)
    }

    fun getKtorSessionAccessToken(): String? = settings.getStringOrNull(KTOR_SESSION_ACCESS_TOKEN)

    fun saveKtorSessionRefreshToken(token: String) {
        settings.putString(KTOR_SESSION_REFRESH_TOKEN, token)
    }
    fun getKtorSessionRefreshToken(): String? = settings.getStringOrNull(KTOR_SESSION_REFRESH_TOKEN)
    // END KTOR SERVER SESSION

    fun saveSessionAccessToken(token: String) {
        settings.putString(SESSION_ACCESS_TOKEN, token)
    }

    fun getSessionAccessToken(): String? = settings.getStringOrNull(SESSION_ACCESS_TOKEN)

    fun saveSessionRefreshToken(token: String) {
        settings.putString(SESSION_REFRESH_TOKEN, token)
    }

    fun getSessionRefreshToken(): String? = settings.getStringOrNull(SESSION_REFRESH_TOKEN)

    fun saveSessionUserId(userId: String) {
        settings.putString(SESSION_USER_ID, userId)
    }

    fun getSessionUserId(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_ID)
    ) { "User ID cannot be null" }

    fun getSettingsNotificationFromAllPartners(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    fun saveSettingsNotificationFromAllPartners(enable: Boolean) {
        settings.putBoolean(SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS, enable)
    }

    fun getSettingsNotificationFromMyPartners(): Boolean = requireNotNull(
        settings.getBooleanOrNull(SETTINGS_NOTIFICATION_FROM_MY_PARTNERS)
    ) { "SettingsNotificationFromAllPartners cannot be null" }

    fun saveSettingsNotificationFromMyPartners(enable: Boolean) {
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

    companion object {
        private const val KTOR_SESSION_ACCESS_TOKEN = "ktor_session_access_token"
        private const val KTOR_SESSION_REFRESH_TOKEN = "ktor_session_refresh_token"
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        private const val SESSION_USER_ID = "session_user_id"
        private const val SETTINGS_NOTIFICATION_FROM_ALL_PARTNERS = "settings_notification_from_all_partners"
        private const val SETTINGS_NOTIFICATION_FROM_MY_PARTNERS = "settings_notification_from_my_partners"
    }
}