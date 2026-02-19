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
            sessionManager.saveSessionAccessToken(token = login.jwtAccessToken)
            sessionManager.saveSessionRefreshToken(token = login.jwtRefreshToken)
            sessionManager.saveSessionUserEmail(email = login.email)
            sessionManager.enableAllNotifications()
        }
    }
}