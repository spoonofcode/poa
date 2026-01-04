package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager

class DeleteAccountUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke() {
        sessionManager.clearSession()
    }
}