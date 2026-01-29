package com.spoonofcode.feature.home.domain.usecase

import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus
import com.spoonofcode.feature.home.domain.repository.RewardRepository

class GetUserDailyCheckInStatusUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> =
        rewardRepository.getUserDailyCheckInStatus()
}