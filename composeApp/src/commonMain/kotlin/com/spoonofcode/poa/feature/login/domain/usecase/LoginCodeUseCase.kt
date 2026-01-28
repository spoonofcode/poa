package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginCodeUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        code: String,
    ): Result<Unit> = loginRepository.loginWithCode(
        email = email,
        loginCode = code,
    ).map { loginResponse ->
        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
        sessionManager.enableAllNotifications()
    }
}