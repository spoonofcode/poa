package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<Unit> = loginRepository.login(
        email = email,
        password = password,
    ).map { loginResponse ->
        sessionManager.saveKtorSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionManager.saveKtorSessionRefreshToken(token = loginResponse.jwtRefreshToken)
        sessionManager.enableAllNotifications()
    }
}