package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.ktor.KtorLoginGoogleDataSource
import com.spoonofcode.poa.feature.login.domain.LoginGoogleDataSource

class LoginGoogleUseCase(
    private val loginGoogleDataSource: LoginGoogleDataSource,
    private val ktorLoginGoogleDataSource: KtorLoginGoogleDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ): Result<Unit> {
        loginGoogleDataSource.create(
            googleUserToken = googleIdToken,
        ).map { loginGoogleResponse ->
            sessionManager.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
            sessionManager.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
            sessionManager.enableAllNotifications()
        }

        return ktorLoginGoogleDataSource.create(
            googleUserToken = googleIdToken,
        ).map { loginGoogleResponse ->
            sessionManager.saveSessionUserId(userId = loginGoogleResponse.userId)
            sessionManager.saveKtorSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
            sessionManager.saveKtorSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
            sessionManager.enableAllNotifications()
        }
    }
}