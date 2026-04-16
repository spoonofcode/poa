package com.spoonofcode.core.data.network

interface SessionManager {
    fun isSessionInitialized(): Boolean
    fun clearSession()
    fun disableAllNotifications()
    fun saveSessionAccessToken(token: String)
    fun getSessionAccessToken(): String?
    fun saveSessionRefreshToken(token: String)
    fun getSessionRefreshToken(): String?
    fun saveSessionUserId(userId: String)
    fun getSessionUserId(): String
    fun saveSessionUserEmail(email: String)
    fun getSessionUserEmail(): String
    fun getNotificationsPersonalizedSettings(): Boolean
    fun saveNotificationsPersonalizedSettings(enable: Boolean)
    fun getNotificationsMyProductSeriesSettings(): Boolean
    fun saveNotificationsMyProductSeriesSettings(enable: Boolean)
    fun getNotificationsFromPartnersSettings(): Boolean
    fun saveNotificationsFromPartnersSettings(enable: Boolean)
}