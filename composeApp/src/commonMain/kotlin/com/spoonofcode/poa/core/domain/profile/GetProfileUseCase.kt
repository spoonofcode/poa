package com.spoonofcode.poa.core.domain.profile

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.network.SessionManager

class GetProfileUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<Profile> = userRepository.readUserProfile(
        userId = sessionManager.getSessionUserId()
    )
}