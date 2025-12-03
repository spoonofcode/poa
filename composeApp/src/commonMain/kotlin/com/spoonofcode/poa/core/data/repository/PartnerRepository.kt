package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.model.User
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@OpenForMokkery
class PartnerRepository : BaseRepository(
    collectionName = "partners",
) {
    suspend fun read(
        partnerId: String,
    ): Partner {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = partnerId,
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Partner.serializer(), responseBody)
        }
    }

    suspend fun update(
        partnerId: String,
        partnerRequest: PartnerRequest
    ): Boolean {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = partnerId,
                method = HttpMethod.Put,
                requestBody =
                    Json.encodeToString(
                        PartnerRequest.serializer(),
                        partnerRequest,
                    )
            )
            responseOrException(response).body<String>()
            true
        }
    }

    suspend fun readAll(): List<Partner> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Partner.serializer()), responseBody)
        }
    }

    suspend fun readPartnerUsers(partnerId: String): List<User> {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = "$partnerId/users",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(User.serializer()), responseBody)
        }
    }
}