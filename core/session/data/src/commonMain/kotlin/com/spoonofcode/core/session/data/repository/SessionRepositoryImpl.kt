package com.spoonofcode.core.session.data.repository

import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.session.data.local.LocalSessionDataSource
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import com.spoonofcode.core.session.domain.repository.SessionRepository
import org.koin.core.component.KoinComponent

class SessionRepositoryImpl(
    private val localSessionDataSource: LocalSessionDataSource,
    private val poaLogger: POALogger,
) : SessionRepository, KoinComponent {
    private val logoutHandlers: List<LogoutHandler> by lazy { getKoin().getAll<LogoutHandler>() }

    override suspend fun isSessionInitialized(): Boolean = getSessionAccessToken() != null

    override suspend fun initSession(
        accessToken: String,
        refreshToken: String,
        userId: String,
        userEmail: String,
    ) {
        poaLogger.info("SessionRepository: Initializing session for user: $userEmail")
        clearSession()
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
        poaLogger.debug("SessionRepository: Updating session tokens")
        localSessionDataSource.updateSessionTokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override suspend fun clearSession() {
        poaLogger.info("SessionRepository: Clearing session")
        localSessionDataSource.clearSession()
        logoutHandlers.forEach {
            runCatching {
                it.onLogout()
            }.onFailure { error ->
                poaLogger.error("SessionRepository: Error in logout handler", error)
            }
        }
    }

    override suspend fun getSessionAccessToken(): String? =
        localSessionDataSource.getSessionAccessToken()

    override suspend fun getSessionRefreshToken(): String? =
        localSessionDataSource.getSessionRefreshToken()

    override suspend fun getSessionUserId(): String = localSessionDataSource.getSessionUserId()

    override suspend fun getSessionUserEmail(): String =
        localSessionDataSource.getSessionUserEmail()
}