package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class GetUserRewardsPointsUseCase(
    private val rewardRepository: RewardRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<Int> {
        return rewardRepository.getUserRewardsPoints(
            userId = sessionManager.getSessionUserId(),
        )
    }
}