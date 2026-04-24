package com.spoonofcode.core.session.data

import com.spoonofcode.core.session.data.local.LocalSessionDataSource
import com.spoonofcode.core.session.domain.SessionRepository

class SessionRepositoryImpl(
    private val localSessionDataSource: LocalSessionDataSource,
) : SessionRepository {

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String
    ) {
        localSessionDataSource.initSession(
            accessToken = accessToken,
            refreshToken = refreshToken,
            userId = userId,
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
}