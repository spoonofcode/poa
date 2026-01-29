package com.spoonofcode.feature.home.domain.repository

import com.spoonofcode.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.feature.reward.data.model.RewardType

interface RewardRepository {
    suspend fun addRewardToUser(rewardType: RewardType): Result<Unit>
    suspend fun getUserRewardsPoints(): Result<Int>
    suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus>
}