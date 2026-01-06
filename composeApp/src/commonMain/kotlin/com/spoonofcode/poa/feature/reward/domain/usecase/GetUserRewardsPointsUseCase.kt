package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class GetUserRewardsPointsUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(): Result<Int> = rewardRepository.getUserRewardsPoints()
}