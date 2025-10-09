package com.spoonofcode.poa.core.domain.login

import com.spoonofcode.poa.core.network.SessionManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() {
        sessionManager.clearSessionUserId()
        sessionManager.clearSessionUserEmail()
        sessionManager.clearSessionAccessToken()
        sessionManager.clearSessionRefreshToken()
    }
}