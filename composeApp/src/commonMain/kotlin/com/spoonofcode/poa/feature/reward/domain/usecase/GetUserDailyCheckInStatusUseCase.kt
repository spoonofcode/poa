package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class GetUserDailyCheckInStatusUseCase(
    private val rewardRepository: RewardRepository,
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> =
        rewardRepository.getUserDailyCheckInStatus()
}