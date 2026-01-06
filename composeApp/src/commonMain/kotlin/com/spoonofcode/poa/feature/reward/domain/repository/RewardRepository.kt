package com.spoonofcode.poa.feature.reward.domain.repository

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType

interface RewardRepository {
    suspend fun addRewardToUser(userId: String, rewardType: RewardType): Result<Unit>
    suspend fun getUserRewardsPoints(userId: String): Result<Int>
    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus>
}