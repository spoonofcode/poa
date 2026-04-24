package com.spoonofcode.core.session.domain


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
}