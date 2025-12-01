package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.AddProductUserRequest
import com.spoonofcode.poa.core.model.AddRewardToUserRequest
import com.spoonofcode.poa.core.model.DailyCheckInStatus
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.model.UserRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
) {
    suspend fun readUserPartners(userId: String): List<Partner> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/partners",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Partner.serializer()), responseBody)
        }
    }

    suspend fun readUserProducts(userId: String): List<Product> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/products",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Product.serializer()), responseBody)
        }
    }

    suspend fun addProductToUser(productId: String, userId: String) {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/products",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    AddProductUserRequest.serializer(),
                    AddProductUserRequest(productId = productId)
                ),
            )
            responseOrException(response).body<String>()
        }
    }

    suspend fun readUserProfile(userId: String): Profile {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/profile",
                method = HttpMethod.Get,
            )
            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Profile.serializer(), responseBody)
        }
    }

    suspend fun readUserProductSeriesIds(userId: String): List<String> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/product-series-ids",
                method = HttpMethod.Get,
            )

            responseOrException(response).body<List<String>>()
        }
    }

    suspend fun addRewardToUser(rewardType: RewardType, userId: String) {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards",
                method = HttpMethod.Post,
                customRequestBody = Json.encodeToString(
                    AddRewardToUserRequest.serializer(),
                    AddRewardToUserRequest(rewardType = rewardType)
                ),
            )
            responseOrException(response).body<String>()
        }
    }

    suspend fun getUserRewardsPoints(userId: String): Int {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards/points",
                method = HttpMethod.Get
            )

            responseOrException(response).body<Int>()
        }
    }

    suspend fun getUserDailyCheckInStatus(userId: String): DailyCheckInStatus {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards/daily-check-in/status",
                method = HttpMethod.Get
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(DailyCheckInStatus.serializer(), responseBody)
        }
    }

}