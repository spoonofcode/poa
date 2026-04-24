package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.data.network.SessionManager

import com.spoonofcode.feature.auth.domain.repository.LoginRepository

class LoginGoogleUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ): Result<Unit> {
        return loginRepository.loginWithGoogle(
            googleUserToken = googleIdToken,
        ).map { login ->
            sessionManager.initSession(
                accessToken = login.jwtAccessToken,
                refreshToken = login.jwtRefreshToken,
                userId = login.id,
            )
        }
    }
}