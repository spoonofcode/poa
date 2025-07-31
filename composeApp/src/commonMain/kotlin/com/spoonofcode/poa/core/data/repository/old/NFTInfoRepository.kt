package com.spoonofcode.poa.core.data.repository.old

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.old.NFTInfo
import com.spoonofcode.poa.core.model.old.NFTInfoRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class NFTInfoRepository : GenericCrudRepository<NFTInfoRequest, NFTInfo>(
    resourceName = "sportEvents",
    requestSerializer = NFTInfoRequest.serializer(),
    responseSerializer = NFTInfo.serializer(),
) {
    suspend fun readNFTInfosCreatedByUser(creatorUserId: Int): List<NFTInfo> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "sportEvents",
                method = HttpMethod.Get,
                queryParams = mapOf("creatorUserId" to creatorUserId.toString())
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(ListSerializer(NFTInfo.serializer()), responseBody)
        }
    }
}