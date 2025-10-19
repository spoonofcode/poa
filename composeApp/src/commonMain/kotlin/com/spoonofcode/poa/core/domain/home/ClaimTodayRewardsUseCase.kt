package com.spoonofcode.poa.core.domain.home

import com.spoonofcode.poa.core.data.repository.RewardsRepository

internal class ClaimTodayRewardUseCase(
    private val repository: RewardsRepository
) {
    suspend operator fun invoke() = repository.claimTodayReward()
}