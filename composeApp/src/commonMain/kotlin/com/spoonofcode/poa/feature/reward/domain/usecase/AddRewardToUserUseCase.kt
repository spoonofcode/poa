package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class AddRewardToUserUseCase(
    private val rewardRepository: RewardRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        rewardType: RewardType,
    ): Result<Unit> {
        return rewardRepository.addRewardToUser(
            rewardType = rewardType,
            userId = sessionManager.getSessionUserId(),
        )
    }
}