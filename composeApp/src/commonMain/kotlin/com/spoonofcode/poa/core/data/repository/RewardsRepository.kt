package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.RewardsMockData
import com.spoonofcode.poa.core.model.DailyReward

class RewardsRepository {
    private var claimedDaysCount = 4

    suspend fun getRewardsStatus(): List<DailyReward> {
        return RewardsMockData.getRewards(claimedDaysCount)
    }

    suspend fun claimTodayReward(): List<DailyReward> {
        claimedDaysCount++
        return getRewardsStatus()
    }
}