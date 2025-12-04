package com.spoonofcode.poa.core.domain.login

import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.network.SessionManager

class RegisterUseCase(
    private val registerRepository: RegisterRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Unit> {
        return registerRepository.create(
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