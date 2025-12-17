package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.LoginDataSource

class LoginUseCase(
    private val loginDataSource: LoginDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<Unit> = loginDataSource.create(
        email = email,
        password = password,
    ).map { loginResponse ->
        sessionManager.saveSessionUserId(userId = loginResponse.userId)
        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
        sessionManager.enableAllNotifications()
    }
}