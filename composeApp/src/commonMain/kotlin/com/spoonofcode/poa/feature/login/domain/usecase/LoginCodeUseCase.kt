package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.LoginCodeDataSource

class LoginCodeUseCase(
    private val loginCodeDataSource: LoginCodeDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        code: String,
    ): Result<Unit> = loginCodeDataSource.create(
        email = email,
        loginCode = code,
    ).map { loginResponse ->
//        sessionManager.saveSessionUserId(userId = loginResponse.userId)
//        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
//        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
//        sessionManager.enableAllNotifications()
    }
}