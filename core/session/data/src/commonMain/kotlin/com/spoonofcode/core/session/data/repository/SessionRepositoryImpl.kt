package com.spoonofcode.core.session.data.repository

import com.spoonofcode.core.session.data.local.LocalSessionDataSource
import com.spoonofcode.core.session.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val localSessionDataSource: LocalSessionDataSource,
) : SessionRepository {

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String,
        userEmail: String,
    ) {
        localSessionDataSource.initSession(
            accessToken = accessToken,
            refreshToken = refreshToken,
            userId = userId,
            userEmail = userEmail,
        )
    }

    override suspend fun updateSessionTokens(
        accessToken: String,
        refreshToken: String
    ) {
        localSessionDataSource.updateSessionTokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override suspend fun clearSession() {
        localSessionDataSource.clearSession()
    }

    override suspend fun getSessionAccessToken(): String? =
        localSessionDataSource.getSessionAccessToken()

    override suspend fun getSessionRefreshToken(): String? =
        localSessionDataSource.getSessionRefreshToken()

    override suspend fun getSessionUserId(): String = localSessionDataSource.getSessionUserId()

    override suspend fun getSessionUserEmail(): String = localSessionDataSource.getSessionUserEmail()
}