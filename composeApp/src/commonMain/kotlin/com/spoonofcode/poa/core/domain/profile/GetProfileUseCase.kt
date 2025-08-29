package com.spoonofcode.poa.core.domain.profile

import com.spoonofcode.poa.core.data.repository.ProfileRepository
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.network.SessionManager

class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Profile = profileRepository.read(
        id = sessionManager.getSessionUserId()
    )
}