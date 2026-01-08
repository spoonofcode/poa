package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke() {
        sessionManager.clearSession()
    }
}