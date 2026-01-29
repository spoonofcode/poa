package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.feature.home.domain.repository.RewardRepository

class GetUserRewardsPointsUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(): Result<Int> = rewardRepository.getUserRewardsPoints()
}