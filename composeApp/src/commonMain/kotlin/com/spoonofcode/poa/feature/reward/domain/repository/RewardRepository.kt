package com.spoonofcode.poa.feature.reward.domain.repository

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType

interface RewardRepository {
    suspend fun addRewardToUser(rewardType: RewardType): Result<Unit>
    suspend fun getUserRewardsPoints(): Result<Int>
    suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus>
}