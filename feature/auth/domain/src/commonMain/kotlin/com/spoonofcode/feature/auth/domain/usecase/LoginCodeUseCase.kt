package com.spoonofcode.feature.auth.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository
import com.spoonofcode.feature.auth.domain.repository.LoginRepository

class LoginCodeUseCase(
    private val loginRepository: LoginRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(
        email: String,
        code: String,
    ): Result<Unit> =
        loginRepository
            .loginWithCode(
                email = email,
                loginCode = code,
            ).map { login ->
                sessionRepository.initSession(
                    accessToken = login.accessToken,
                    refreshToken = login.refreshToken,
                    userId = login.id,
                    userEmail = login.email,
                )
            }
}
