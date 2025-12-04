package com.spoonofcode.poa.core.domain.user.reward

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager

internal class GetUserRewardsPointsUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<Int> {
        val userId = sessionManager.getSessionUserId()
        return userRepository.getUserRewardsPoints(userId)
    }
}