package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<Profile> = profileRepository.readUserProfile(
        userId = sessionManager.getSessionUserId()
    )
}