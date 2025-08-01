package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class PartnerRepository : GenericCrudRepository<PartnerRequest, Partner>(
    resourceName = "partners",
    requestSerializer = PartnerRequest.serializer(),
    responseSerializer = Partner.serializer(),
) {
    suspend fun readPartnersOwnedByUser(ownerUserId: Int): List<Partner> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "partners",
                method = HttpMethod.Get,
                queryParams = mapOf("ownerUserId" to ownerUserId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(Partner.serializer()), responseBody)
        }
    }
}