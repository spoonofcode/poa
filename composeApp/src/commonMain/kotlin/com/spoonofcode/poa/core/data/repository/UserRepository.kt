package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.AddProductUserRequest
import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product
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
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/partners",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Partner.serializer()), responseBody)
        }
    }

    suspend fun readUserProducts(userId: String): List<Product> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/products",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Product.serializer()), responseBody)
        }
    }

    suspend fun addProductToUser(productId: String, userId: String) {
        return withContext(Dispatchers.IO) {
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

    suspend fun getUserPoints(userId: String): Int {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/points",
                method = HttpMethod.Get
            )
            responseOrException(response).body<Int>()
        }
    }

    suspend fun readUserProductSeriesIds(userId: String): List<String> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/product-series-ids",
                method = HttpMethod.Get,
            )

            responseOrException(response).body<List<String>>()
        }
    }

    suspend fun getRewardsStatus(userId: String): List<DailyReward> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards",
                method = HttpMethod.Get
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(DailyReward.serializer()), responseBody)
        }
    }

    suspend fun claimTodayReward(userId: String): List<DailyReward> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards/claim",
                method = HttpMethod.Post
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(DailyReward.serializer()), responseBody)
        }
    }
}