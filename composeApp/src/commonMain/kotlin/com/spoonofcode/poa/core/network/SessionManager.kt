package com.spoonofcode.poa.core.network

import com.russhwolf.settings.Settings
import com.spoonofcode.poa.core.test.OpenForMokkery

@OpenForMokkery
class SessionManager(private val settings: Settings) {
    fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    fun saveSessionAccessToken(token: String) {
        settings.putString(SESSION_ACCESS_TOKEN, token)
    }

    fun getSessionAccessToken(): String? {
        return settings.getStringOrNull(SESSION_ACCESS_TOKEN)
    }

    fun clearSessionAccessToken() {
        settings.remove(SESSION_ACCESS_TOKEN)
    }

    fun saveSessionRefreshToken(token: String) {
        settings.putString(SESSION_REFRESH_TOKEN, token)
    }

    fun getSessionRefreshToken(): String? {
        return settings.getStringOrNull(SESSION_REFRESH_TOKEN)
    }

    fun clearSessionRefreshToken() {
        settings.remove(SESSION_REFRESH_TOKEN)
    }

    fun saveSessionUserId(userId: Int) {
        settings.putInt(SESSION_USER_ID, userId)
    }

    fun getSessionUserId(): Int = requireNotNull(
        settings.getIntOrNull(SESSION_USER_ID)
    ) { "User ID cannot be null" }

    fun clearSessionUserId() {
        settings.remove(SESSION_USER_ID)
    }

    fun saveSessionUserEmail(userEmail: String) {
        settings.putString(SESSION_USER_EMAIL, userEmail)
    }

    fun getSessionUserEmail(): String = requireNotNull(
        settings.getStringOrNull(SESSION_USER_EMAIL)
    ) { "User email cannot be null" }

    fun clearSessionUserEmail() {
        settings.remove(SESSION_USER_EMAIL)
    }

    companion object {
        private const val SESSION_ACCESS_TOKEN = "session_access_token"
        private const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        private const val SESSION_USER_ID = "session_user_id"
        private const val SESSION_USER_EMAIL = "session_user_email"
    }
}