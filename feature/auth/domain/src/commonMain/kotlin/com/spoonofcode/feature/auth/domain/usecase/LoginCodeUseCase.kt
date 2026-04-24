package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.feature.auth.domain.repository.LoginRepository

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
    ).map { login ->
        sessionManager.initSession(
            accessToken = login.jwtAccessToken,
            refreshToken = login.jwtRefreshToken,
            userId = login.id,
        )
    }
}