package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.model.User
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
class PartnerRepository : GenericCrudRepository<PartnerRequest, Partner>(
    resourceName = "partners",
    requestSerializer = PartnerRequest.serializer(),
    responseSerializer = Partner.serializer(),
) {
    suspend fun readPartnerUsers(partnerId: String): List<User> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "partners/$partnerId/users",
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(User.serializer()), responseBody)
        }
    }
}