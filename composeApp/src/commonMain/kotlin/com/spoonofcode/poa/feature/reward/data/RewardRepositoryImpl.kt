package com.spoonofcode.poa.feature.reward.data

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.feature.reward.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class RewardRepositoryImpl(
    private val remoteUserRewardDataSource: RemoteUserRewardDataSource,
) : RewardRepository {
    override suspend fun addRewardToUser(rewardType: RewardType): Result<Unit> =
        remoteUserRewardDataSource.addRewardToUser(rewardType)

    override suspend fun getUserRewardsPoints(): Result<Int> =
        remoteUserRewardDataSource.getUserRewardsPoints()

    override suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus> =
        remoteUserRewardDataSource.getUserDailyCheckInStatus()
}