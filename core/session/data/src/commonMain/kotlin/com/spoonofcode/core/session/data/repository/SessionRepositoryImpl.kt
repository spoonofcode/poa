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
        localSessionDataSource.updateSessionTokens(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override suspend fun clearSession() {
        poaLogger.info("SessionRepository: Starting session clear")
        localSessionDataSource.clearSession()
        logoutHandlers.forEach { handler ->
            val handlerName = handler::class.simpleName
            poaLogger.debug("SessionRepository: Triggering logout handler: $handlerName")
            runCatching {
                handler.onLogout()
            }.onSuccess {
                poaLogger.debug("SessionRepository: Logout handler $handlerName finished successfully")
            }.onFailure { e ->
                poaLogger.error("SessionRepository: Logout handler $handlerName failed", e)
            }
        }
        poaLogger.info("SessionRepository: Session cleared")
    }

    override suspend fun getSessionAccessToken(): String? =
        localSessionDataSource.getSessionAccessToken()

    override suspend fun getSessionRefreshToken(): String? =
        localSessionDataSource.getSessionRefreshToken()

    override suspend fun getSessionUserId(): String = localSessionDataSource.getSessionUserId()

    override suspend fun getSessionUserEmail(): String =
        localSessionDataSource.getSessionUserEmail()
}