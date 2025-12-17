package com.spoonofcode.poa.feature.login.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.login.domain.RegisterDataSource

class RegisterUseCase(
    private val registerDataSource: RegisterDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Unit> {
        return registerDataSource.create(
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