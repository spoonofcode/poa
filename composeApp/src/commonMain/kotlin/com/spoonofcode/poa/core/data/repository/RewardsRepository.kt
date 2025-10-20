package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.DailyReward
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class RewardsRepository : GenericCrudRepository<Unit, DailyReward>(
    resourceName = "users",
    requestSerializer = Unit.serializer(),
    responseSerializer = DailyReward.serializer(),
) {

    /**
     * Pobiera status nagród z backendu dla konkretnego użytkownika.
     */
    suspend fun getRewardsStatus(userId: Int): List<DailyReward> {
        println(">>> LOG: RewardsRepository - pobieram nagrody dla userId=$userId")

        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "users/$userId/rewards",
                method = HttpMethod.Get
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(DailyReward.serializer()), responseBody)
        }
    }

    /**
     * Wysyła żądanie odebrania nagrody do backendu dla konkretnego użytkownika.
     */
    suspend fun claimTodayReward(userId: Int): List<DailyReward> {
        println(">>> LOG: RewardsRepository - odbieram dzisiejszą nagrodę dla userId=$userId")

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
