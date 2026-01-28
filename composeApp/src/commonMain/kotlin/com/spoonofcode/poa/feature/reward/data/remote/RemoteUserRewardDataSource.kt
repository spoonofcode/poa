package com.spoonofcode.poa.feature.reward.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.reward.data.model.AddRewardToUserRequest
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import io.ktor.http.HttpMethod

class RemoteUserRewardDataSource : RemoteBaseDataSource(
    collectionName = "users",
) {
    suspend fun addRewardToUser(rewardType: RewardType): Result<Unit> = doRequest(
        urlPostfixPath = "rewards",
        method = HttpMethod.Post,
        requestBody = AddRewardToUserRequest(rewardType = rewardType),
    )

    suspend fun getUserRewardsPoints(): Result<Int> = doRequest(
        urlPostfixPath = "rewards/points",
        method = HttpMethod.Get,
    )

    suspend fun getUserDailyCheckInStatus(): Result<DailyCheckInStatus> = doRequest(
        urlPostfixPath = "rewards/daily-check-in/status",
        method = HttpMethod.Get,
    )
}