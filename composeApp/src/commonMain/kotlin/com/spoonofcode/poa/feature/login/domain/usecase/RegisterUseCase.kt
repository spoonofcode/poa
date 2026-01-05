package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.repository.LoginRepository

class RegisterUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Unit> {
        return loginRepository.register(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
        ).map { registerResponse ->
            sessionManager.saveSessionUserId(userId = registerResponse.userId)
            sessionManager.saveSessionAccessToken(token = registerResponse.jwtAccessToken)
            sessionManager.saveSessionRefreshToken(token = registerResponse.jwtRefreshToken)
        }
    }
}