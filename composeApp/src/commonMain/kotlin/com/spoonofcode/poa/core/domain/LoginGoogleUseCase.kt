package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.LoginGoogleRepository
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import com.spoonofcode.poa.core.network.SessionManager

class LoginGoogleUseCase(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ) {
        val loginGoogleResponse = loginGoogleRepository.create(
            request = LoginGoogleRequest(
                googleUserToken = googleIdToken,
            )
        )
        sessionManager.saveSessionUserId(userId = loginGoogleResponse.userId)
        sessionManager.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
    }
}