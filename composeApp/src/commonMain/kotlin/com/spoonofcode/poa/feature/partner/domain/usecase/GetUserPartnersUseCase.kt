package com.spoonofcode.poa.feature.partner.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class GetUserPartnersUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Partner>> = profileRepository.readUserPartners(
        userId = sessionManager.getSessionUserId(),
    )
}