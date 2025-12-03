package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.FAQ
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class FAQRepository : BaseRepository(
    collectionName = "faqs",
) {
    suspend fun readAll(): List<FAQ> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(FAQ.serializer()), responseBody)
        }
    }
}