package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.network.SessionManager

class DeleteAccountUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke() {
        sessionManager.clearSession()
    }
}