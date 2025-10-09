package com.spoonofcode.poa.core.domain.login

import com.spoonofcode.poa.core.data.repository.RegisterRepository
import com.spoonofcode.poa.core.model.RegisterRequest
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
    ) {
        val registerResponse = registerRepository.create(
            RegisterRequest(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
            )
        )
        sessionManager.saveSessionUserId(userId = registerResponse.userId)
        sessionManager.saveSessionUserEmail(userEmail = registerResponse.userEmail)
        sessionManager.saveSessionAccessToken(token = registerResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = registerResponse.jwtRefreshToken)
    }
}