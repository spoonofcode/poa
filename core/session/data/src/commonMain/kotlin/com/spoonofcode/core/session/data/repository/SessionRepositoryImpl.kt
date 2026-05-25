package com.spoonofcode.core.session.data.repository

import com.spoonofcode.core.session.data.local.LocalSessionDataSource
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import com.spoonofcode.core.session.domain.repository.SessionRepository
import org.koin.core.component.KoinComponent

class SessionRepositoryImpl(
    private val localSessionDataSource: LocalSessionDataSource,
) : SessionRepository, KoinComponent {

    private val logoutHandlers: List<LogoutHandler> by lazy { getKoin().getAll<LogoutHandler>() }

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String,
        userEmail: String,
    ) {
        // 1. Initialize the new session first in persistent storage
        localSessionDataSource.initSession(
            accessToken = accessToken,
            refreshToken = refreshToken,
            userId = userId,
            userEmail = userEmail,
        )

        // 2. Clear memory caches of the previous session AFTER writing the new tokens.
        // This ensures that any subsequent loadTokens call by HttpClient will see the new values.
        logoutHandlers.forEach { runCatching { it.onLogout() } }
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
        // 1. Clear persistent storage first
        localSessionDataSource.clearSession()

        // 2. Then clear memory caches and platform states
        logoutHandlers.forEach { runCatching { it.onLogout() } }
    }

    override suspend fun getSessionAccessToken(): String? =
        localSessionDataSource.getSessionAccessToken()

    override suspend fun getSessionRefreshToken(): String? =
        localSessionDataSource.getSessionRefreshToken()

    override suspend fun getSessionUserId(): String = localSessionDataSource.getSessionUserId()

    override suspend fun getSessionUserEmail(): String = localSessionDataSource.getSessionUserEmail()
}