package com.spoonofcode.poa.feature.profile.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.profile.data.model.User
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.reward.data.model.AddRewardToUserRequest
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import io.ktor.http.HttpMethod

class RemoteUserDataSource : RemoteBaseDataSource(
    collectionName = "users"
) {
    suspend fun read(userId: String): Result<User> = doRequest(
        urlPostfixPath = userId,
        method = HttpMethod.Get,
    )


    suspend fun readUserProfile(userId: String): Result<Profile> = doRequest(
        urlPostfixPath = "/$userId/profile",
        method = HttpMethod.Get,
    )

    suspend fun addRewardToUser(rewardType: RewardType, userId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "/$userId/rewards",
            method = HttpMethod.Post,
            requestBody = AddRewardToUserRequest(rewardType = rewardType),
        )

    suspend fun getUserRewardsPoints(userId: String): Result<Int> = doRequest(
        urlPostfixPath = "/$userId/rewards/points",
        method = HttpMethod.Get,
    )

    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> =
        doRequest(
            urlPostfixPath = "/$userId/rewards/daily-check-in/status",
            method = HttpMethod.Get,
        )
}