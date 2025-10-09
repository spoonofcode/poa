package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.network.SessionManager

class DeleteAccountUseCase(
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() {
        sessionManager.clearSessionUserId()
        sessionManager.clearSessionUserEmail()
        sessionManager.clearSessionAccessToken()
        sessionManager.clearSessionRefreshToken()
    }
}