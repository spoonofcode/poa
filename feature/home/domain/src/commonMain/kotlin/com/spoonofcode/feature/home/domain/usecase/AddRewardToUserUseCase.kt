package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.feature.home.domain.model.RewardType
import com.spoonofcode.feature.home.domain.repository.RewardRepository

class AddRewardToUserUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(
        rewardType: RewardType,
    ): Result<Unit> = rewardRepository.addRewardToUser(rewardType = rewardType)
}