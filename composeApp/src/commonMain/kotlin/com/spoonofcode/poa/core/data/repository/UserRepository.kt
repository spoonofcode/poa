package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.data.model.AddProductToUserRequest
import com.spoonofcode.poa.core.data.model.AddRewardToUserRequest
import com.spoonofcode.poa.core.model.DailyCheckInStatus
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class UserRepository : BaseRepository(
    collectionName = "users"
) {
    suspend fun read(
        userId: String,
    ): Result<User> = doRequest(
        urlPostfixPath = userId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<User>()
    )

    suspend fun readUserPartners(userId: String): Result<List<Partner>> = doRequest(
        urlPostfixPath = "$userId/partners",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<Partner>>(),
    )

    suspend fun readUserProducts(userId: String): Result<List<Product>> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<Product>>(),
    )

    suspend fun addProductToUser(productId: String, userId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Post,
        requestBody = AddProductToUserRequest(productId = productId),
        responseTypeInfo = typeInfo<Unit>()
    )

    suspend fun deleteProductFromUser(productId: String, userId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Delete,
        requestBody = AddProductToUserRequest(productId = productId),
        responseTypeInfo = typeInfo<Unit>()
    )

    suspend fun readUserProfile(userId: String): Result<Profile> = doRequest(
        urlPostfixPath = "$userId/profile",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Profile>()
    )

    suspend fun readUserProductSeriesIds(userId: String): Result<List<String>> = doRequest(
        urlPostfixPath = "$userId/product-series-ids",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<List<String>>(),
    )

    suspend fun addRewardToUser(rewardType: RewardType, userId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/rewards",
        method = HttpMethod.Post,
        requestBody = AddRewardToUserRequest(rewardType = rewardType),
        responseTypeInfo = typeInfo<Unit>()
    )

    suspend fun getUserRewardsPoints(userId: String): Result<Int> = doRequest(
        urlPostfixPath = "$userId/rewards/points",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Int>(),
    )

    suspend fun getUserDailyCheckInStatus(userId: String): Result<DailyCheckInStatus> = doRequest(
        urlPostfixPath = "$userId/rewards/daily-check-in/status",
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<DailyCheckInStatus>(),
    )

}