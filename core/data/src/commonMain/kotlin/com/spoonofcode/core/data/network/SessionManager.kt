package com.spoonofcode.core.data.network

interface SessionManager {
    suspend fun isSessionInitialized(): Boolean
    suspend fun clearSession()
    suspend fun saveSessionAccessToken(token: String)
    suspend fun getSessionAccessToken(): String?
    suspend fun saveSessionRefreshToken(token: String)
    suspend fun getSessionRefreshToken(): String?
    suspend fun saveSessionUserId(userId: String)
    suspend fun getSessionUserId(): String
    suspend fun saveSessionUserEmail(email: String)
    suspend fun getSessionUserEmail(): String
    suspend fun getNotificationsPersonalizedSettings(): Boolean
    suspend fun saveNotificationsPersonalizedSettings(enable: Boolean)
    suspend fun getNotificationsMyProductSeriesSettings(): Boolean
    suspend fun saveNotificationsMyProductSeriesSettings(enable: Boolean)
    suspend fun getNotificationsFromPartnersSettings(): Boolean
    suspend fun saveNotificationsFromPartnersSettings(enable: Boolean)
}