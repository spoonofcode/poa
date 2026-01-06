package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class GetUserDailyCheckInStatusUseCase(
    private val rewardRepository: RewardRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> {
        return rewardRepository.getUserDailyCheckInStatus(
            userId = sessionManager.getSessionUserId(),
        )
    }
}