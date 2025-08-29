package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.GenericCrudRepository
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.model.ProductRequest
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ProductRepository : GenericCrudRepository<ProductRequest, Product>(
    resourceName = "products",
    requestSerializer = ProductRequest.serializer(),
    responseSerializer = Product.serializer(),
) {
    suspend fun readProductByTagId(tagId: String): Product {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = doRequest(
                urlPath = "products",
                method = HttpMethod.Get,
                queryParams = mapOf("tagId" to tagId)
            )

            val responseBody = responseOrException(response).body<String>()
            Json.decodeFromString(Product.serializer(), responseBody)
        }
    }
}