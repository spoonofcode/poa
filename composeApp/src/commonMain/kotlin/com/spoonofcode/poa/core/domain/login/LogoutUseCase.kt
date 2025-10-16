package com.spoonofcode.poa.core.domain.login

import com.spoonofcode.poa.core.network.SessionManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke() {
        sessionManager.clearSession()
    }
}