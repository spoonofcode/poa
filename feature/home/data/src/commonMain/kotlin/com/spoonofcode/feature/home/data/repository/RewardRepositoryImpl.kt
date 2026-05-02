package com.spoonofcode.feature.home.data.repository

import com.spoonofcode.feature.home.data.remote.RemoteUserRewardDataSource
import com.spoonofcode.feature.home.data.testdata.DAILY_CHECK_IN_STATUS
import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus
import com.spoonofcode.feature.home.domain.model.RewardType
import com.spoonofcode.feature.home.domain.repository.RewardRepository

class RewardRepositoryImpl(
    private val remoteUserRewardDataSource: RemoteUserRewardDataSource,
) : RewardRepository {

    // TODO #99 Adopt poa backend
    override suspend fun addRewardToUser(rewardType: RewardType): Result<Unit> =
        Result.success(Unit)
//        remoteUserRewardDataSource.addRewardToUser(rewardType)

    // TODO #99 Adopt poa backend
    override suspend fun getUserRewardsPoints(): Result<Int> =
        Result.success(0)
//        remoteUserRewardDataSource.getUserRewardsPoints()

    // TODO #99 Adopt poa backend
    override suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus> =
            Result.success(DAILY_CHECK_IN_STATUS)
//        remoteUserRewardDataSource.getUserDailyCheckInStatus()
}