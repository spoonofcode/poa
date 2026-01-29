package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.data.network.SessionManager

class DeleteAccountUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke() {
        sessionManager.clearSession()
    }
}