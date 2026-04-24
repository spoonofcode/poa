package com.spoonofcode.core.session.domain

import com.spoonofcode.core.session.domain.model.NotificationSettings

interface SessionRepository {
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
    suspend fun getNotificationSettings(): NotificationSettings
    suspend fun setNotificationSettings(
        notificationsPersonalizedEnabled: Boolean,
        notificationsFromPartnersEnabled: Boolean,
        notificationsMyProductSeriesEnabled: Boolean
    )
}