package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.LoginRequestCodeDataSource

class LoginRequestCodeUseCase(
    private val loginRequestCodeDataSource: LoginRequestCodeDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<Unit> = loginRequestCodeDataSource.create(
        email = email,
        recaptchaToken = ""
    ).map { loginResponse ->
//        sessionManager.saveSessionUserId(userId = loginResponse.userId)
//        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
//        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
//        sessionManager.enableAllNotifications()
    }
}