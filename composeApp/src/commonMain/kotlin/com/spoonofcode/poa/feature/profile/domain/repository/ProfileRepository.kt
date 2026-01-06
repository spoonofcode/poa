package com.spoonofcode.poa.feature.profile.domain.repository

import com.spoonofcode.poa.feature.profile.data.model.User
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType

interface ProfileRepository {
    suspend fun read(userId: String): Result<User>
    suspend fun readUserProfile(userId: String): Result<Profile>
    suspend fun addRewardToUser(rewardType: RewardType, userId: String): Result<Unit>
    suspend fun getUserRewardsPoints(userId: String): Result<Int>
    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus>
}