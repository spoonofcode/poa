package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.AddProductUserRequest
import com.spoonofcode.poa.core.model.AddRewardToUserRequest
import com.spoonofcode.poa.core.model.DailyCheckInStatus
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class UserRepository : BaseRepository(
    collectionName = "users"
) {
    suspend fun read(
        userId: String,
    ): User {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = userId,
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(User.serializer(), responseBody)
        }
    }

    suspend fun readUserPartners(userId: String): List<Partner> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/partners",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Partner.serializer()), responseBody)
        }
    }

    suspend fun readUserProducts(userId: String): List<Product> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/products",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Product.serializer()), responseBody)
        }
    }

    suspend fun addProductToUser(productId: String, userId: String) {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/products",
                method = HttpMethod.Post,
                requestBody = Json.encodeToString(
                    AddProductUserRequest.serializer(),
                    AddProductUserRequest(productId = productId)
                ),
            )
            responseOrException(response).body<String>()
        }
    }

    suspend fun deleteProductFromUser(productId: String, userId: String) {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/products",
                method = HttpMethod.Delete,
                requestBody = Json.encodeToString(
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
                urlPostfixPath = "$userId/profile",
                method = HttpMethod.Get,
            )
            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Profile.serializer(), responseBody)
        }
    }

    suspend fun readUserProductSeriesIds(userId: String): List<String> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/product-series-ids",
                method = HttpMethod.Get,
            )

            responseOrException(response).body<List<String>>()
        }
    }

    suspend fun addRewardToUser(rewardType: RewardType, userId: String) {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/rewards",
                method = HttpMethod.Post,
                requestBody = Json.encodeToString(
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
                urlPostfixPath = "$userId/rewards/points",
                method = HttpMethod.Get
            )

            responseOrException(response).body<Int>()
        }
    }

    suspend fun getUserDailyCheckInStatus(userId: String): DailyCheckInStatus {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$userId/rewards/daily-check-in/status",
                method = HttpMethod.Get
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(DailyCheckInStatus.serializer(), responseBody)
        }
    }

}