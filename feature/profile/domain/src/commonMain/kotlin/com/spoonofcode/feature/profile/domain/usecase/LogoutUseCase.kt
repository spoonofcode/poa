package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.data.network.SessionManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() {
        sessionManager.clearSession()
    }
}