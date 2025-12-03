package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.ProductRequest
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@OpenForMokkery
class ProductRepository : BaseRepository(
    collectionName = "products",
) {
    suspend fun read(
        productId: String,
    ): Product {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = productId,
                method = HttpMethod.Get,
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Product.serializer(), responseBody)
        }
    }

    suspend fun update(
        productId: String,
        productRequest: ProductRequest
    ): Boolean {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                urlPostfixPath = productId,
                method = HttpMethod.Put,
                requestBody =
                    Json.encodeToString(
                        ProductRequest.serializer(),
                        productRequest,
                    )
            )
            responseOrException(response).body<String>()
            true
        }
    }

    suspend fun readProductByTagId(tagId: String): Product {
        return withContext(dispatcherProvider.io()) {
            val response: HttpResponse = doRequest(
                method = HttpMethod.Get,
                queryParams = mapOf("tagId" to tagId)
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Product.serializer(), responseBody)
        }
    }
}