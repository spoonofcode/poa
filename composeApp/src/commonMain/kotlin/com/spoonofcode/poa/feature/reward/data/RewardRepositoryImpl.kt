package com.spoonofcode.poa.feature.reward.data

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.feature.reward.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.poa.feature.reward.data.testdata.DAILY_CHECK_IN_STATUS
import com.spoonofcode.poa.feature.reward.domain.repository.RewardRepository

class RewardRepositoryImpl(
    private val remoteUserRewardDataSource: RemoteUserRewardDataSource,
) : RewardRepository {
    override suspend fun addRewardToUser(rewardType: RewardType): Result<Unit> =
        remoteUserRewardDataSource.addRewardToUser(rewardType)

    // TODO #99 Adopt poa backend
    override suspend fun getUserRewardsPoints(): Result<Int> =
        Result.success(0)
//        remoteUserRewardDataSource.getUserRewardsPoints()

    override suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus> =
            Result.success(DAILY_CHECK_IN_STATUS)
//        remoteUserRewardDataSource.getUserDailyCheckInStatus()
}