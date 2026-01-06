package com.spoonofcode.poa.feature.reward.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.reward.data.model.AddRewardToUserRequest
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import io.ktor.http.HttpMethod

class RemoteUserRewardDataSource : RemoteBaseDataSource(
    collectionName = "users",
) {
    suspend fun addRewardToUser(userId: String, rewardType: RewardType): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/rewards",
        method = HttpMethod.Post,
        requestBody = AddRewardToUserRequest(rewardType = rewardType),
    )

    suspend fun getUserRewardsPoints(userId: String): Result<Int> = doRequest(
        urlPostfixPath = "$userId/rewards/points",
        method = HttpMethod.Get,
    )

    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> = doRequest(
        urlPostfixPath = "$userId/rewards/daily-check-in/status",
        method = HttpMethod.Get,
    )
}