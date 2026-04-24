package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.data.network.SessionRepository

import com.spoonofcode.feature.auth.domain.repository.LoginRepository

class LoginGoogleUseCase(
    private val loginRepository: LoginRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ): Result<Unit> {
        return loginRepository.loginWithGoogle(
            googleUserToken = googleIdToken,
        ).map { login ->
            sessionRepository.initSession(
                accessToken = login.jwtAccessToken,
                refreshToken = login.jwtRefreshToken,
                userId = login.id,
            )
        }
    }
}