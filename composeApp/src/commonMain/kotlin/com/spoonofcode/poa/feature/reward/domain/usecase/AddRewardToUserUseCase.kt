package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class AddRewardToUserUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(
        rewardType: RewardType,
    ): Result<Unit> = rewardRepository.addRewardToUser(rewardType = rewardType)
}