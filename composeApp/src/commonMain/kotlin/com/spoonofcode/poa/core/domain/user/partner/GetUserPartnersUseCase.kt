package com.spoonofcode.poa.core.domain.user.partner

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.network.SessionManager

class GetUserPartnersUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): List<Partner> = userRepository.readUserPartners(
        userId = sessionManager.getSessionUserId(),
    )
}