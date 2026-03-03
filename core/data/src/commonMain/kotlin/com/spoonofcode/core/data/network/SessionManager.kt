package com.spoonofcode.core.data.network

interface SessionManager {
    fun isSessionInitialized(): Boolean
    fun clearSession()
    fun enableAllNotifications()
    fun saveSessionAccessToken(token: String)
    fun getSessionAccessToken(): String?
    fun saveSessionRefreshToken(token: String)
    fun getSessionRefreshToken(): String?
    fun saveSessionUserId(userId: String)
    fun getSessionUserId(): String
    fun saveSessionUserEmail(email: String)
    fun getSessionUserEmail(): String
    fun getSettingsNotificationFromAllPartners(): Boolean
    fun saveSettingsNotificationFromAllPartners(enable: Boolean)
    fun getSettingsNotificationFromMyPartners(): Boolean
    fun saveSettingsNotificationFromMyPartners(enable: Boolean)
}