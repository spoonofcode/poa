package com.spoonofcode.poa.feature.profile.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.partner.domain.model.Partner
import com.spoonofcode.poa.feature.product.data.remote.model.AddProductToUserRequest
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.profile.data.model.User
import com.spoonofcode.poa.feature.profile.domain.UserDataSource
import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.reward.data.model.AddRewardToUserRequest
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.feature.reward.data.model.RewardType
import io.ktor.http.HttpMethod

class RemoteUserDataSource : RemoteBaseDataSource(
    collectionName = "users"
), UserDataSource {
    override suspend fun read(userId: String): Result<User> = doRequest(
        urlPostfixPath = userId,
        method = HttpMethod.Get,
    )

    override suspend fun readUserPartners(userId: String): Result<List<Partner>> = doRequest(
        urlPostfixPath = "$userId/partners",
        method = HttpMethod.Get,
    )

    override suspend fun readUserProducts(userId: String): Result<List<Product>> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Get,
    )

    override suspend fun addProductToUser(productId: String, userId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "$userId/products",
            method = HttpMethod.Post,
            requestBody = AddProductToUserRequest(productId = productId),
        )

    override suspend fun deleteProductFromUser(productId: String, userId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "$userId/products",
            method = HttpMethod.Delete,
            requestBody = AddProductToUserRequest(productId = productId),
        )

    override suspend fun readUserProfile(userId: String): Result<Profile> = doRequest(
        urlPostfixPath = "$userId/profile",
        method = HttpMethod.Get,
    )

    override suspend fun readUserProductSeriesIds(userId: String): Result<List<String>> = doRequest(
        urlPostfixPath = "$userId/product-series-ids",
        method = HttpMethod.Get,
    )

    override suspend fun addRewardToUser(rewardType: RewardType, userId: String): Result<Unit> =
        doRequest(
            urlPostfixPath = "$userId/rewards",
            method = HttpMethod.Post,
            requestBody = AddRewardToUserRequest(rewardType = rewardType),
        )

    override suspend fun getUserRewardsPoints(userId: String): Result<Int> = doRequest(
        urlPostfixPath = "$userId/rewards/points",
        method = HttpMethod.Get,
    )

    override suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> =
        doRequest(
            urlPostfixPath = "$userId/rewards/daily-check-in/status",
            method = HttpMethod.Get,
        )
}