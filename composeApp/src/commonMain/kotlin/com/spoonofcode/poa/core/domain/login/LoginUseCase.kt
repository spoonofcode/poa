package com.spoonofcode.poa.core.domain.login

import com.spoonofcode.poa.core.data.repository.LoginRepository
import com.spoonofcode.poa.core.model.LoginRequest
import com.spoonofcode.poa.core.network.SessionManager

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ) {
        val loginResponse = loginRepository.create(
            LoginRequest(
                email = email,
                password = password,
            )
        )
        sessionManager.saveSessionUserId(userId = loginResponse.userId)
        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
    }
}