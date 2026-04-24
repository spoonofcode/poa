package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.core.data.network.SessionRepository

class LogoutUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke() {
        sessionRepository.clearSession()
    }
}