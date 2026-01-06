package com.spoonofcode.poa.feature.reward.data

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.feature.reward.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class RewardRepositoryImpl(
    private val remoteUserRewardDataSource: RemoteUserRewardDataSource,
) : RewardRepository {
    override suspend fun addRewardToUser(userId: String, rewardType: RewardType): Result<Unit> =
        remoteUserRewardDataSource.addRewardToUser(userId, rewardType)

    override suspend fun getUserRewardsPoints(userId: String): Result<Int> =
        remoteUserRewardDataSource.getUserRewardsPoints(userId)

    override suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> =
        remoteUserRewardDataSource.getUserDailyCheckInStatus(userId)
}