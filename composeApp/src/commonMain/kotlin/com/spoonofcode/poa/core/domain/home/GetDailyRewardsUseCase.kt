package com.spoonofcode.poa.core.domain.home

import com.spoonofcode.poa.core.data.repository.RewardsRepository
import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.network.SessionManager

internal class GetDailyRewardsUseCase(
    private val repository: RewardsRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): List<DailyReward> {
        val userId = sessionManager.getSessionUserId()
        return repository.getRewardsStatus(userId)
    }
}