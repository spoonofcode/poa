package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.core.network.SessionManager

class GetProfileUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<Profile> = userRepository.readUserProfile(
        userId = sessionManager.getSessionUserId()
    )
}