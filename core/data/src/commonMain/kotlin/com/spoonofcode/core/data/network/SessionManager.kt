package com.spoonofcode.core.data.network

interface SessionManager {
    suspend fun isSessionInitialized(): Boolean
    suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String
    )
    suspend fun updateSessionTokens(
        accessToken: String,
        refreshToken: String,
    )
    suspend fun clearSession()
    suspend fun getSessionAccessToken(): String?
    suspend fun getSessionRefreshToken(): String?
    suspend fun getSessionUserId(): String
    suspend fun getNotificationsPersonalizedSettings(): Boolean
    suspend fun getNotificationsMyProductSeriesSettings(): Boolean
    suspend fun getNotificationsFromPartnersSettings(): Boolean
    suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    )
}