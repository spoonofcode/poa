package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class LoginGoogleUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ): Result<Unit> {
        return loginRepository.loginWithGoogle(
            googleUserToken = googleIdToken,
        ).map { loginGoogleResponse ->
            sessionManager.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
            sessionManager.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
            sessionManager.enableAllNotifications()
        }
    }
}