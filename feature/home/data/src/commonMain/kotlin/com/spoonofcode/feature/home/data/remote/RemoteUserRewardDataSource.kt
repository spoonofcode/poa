package com.spoonofcode.feature.home.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.home.data.model.AddRewardToUserRequest
import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus
import com.spoonofcode.feature.home.domain.model.RewardType
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