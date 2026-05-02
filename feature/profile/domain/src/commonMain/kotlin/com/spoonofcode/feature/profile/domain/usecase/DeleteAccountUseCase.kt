package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.session.domain.repository.SessionRepository

class DeleteAccountUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke() {
        sessionRepository.clearSession()
    }
}