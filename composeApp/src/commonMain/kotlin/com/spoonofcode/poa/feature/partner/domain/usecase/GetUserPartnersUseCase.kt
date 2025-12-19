package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.partner.domain.model.Partner

class GetUserPartnersUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Partner>> = userRepository.readUserPartners(
        userId = sessionManager.getSessionUserId(),
    )
}