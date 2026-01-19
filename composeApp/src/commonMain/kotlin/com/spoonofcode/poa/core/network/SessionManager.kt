package com.spoonofcode.poa.core.network

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

    fun getSettingsNotificationFromAllPartners(): Boolean

    fun saveSettingsNotificationFromAllPartners(enable: Boolean)

    fun getSettingsNotificationFromMyPartners(): Boolean

    fun saveSettingsNotificationFromMyPartners(enable: Boolean)
}