package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.network.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.AddProductToUserRequest
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.feature.product.data.remote.model.UpdateUserProductRequest
import io.ktor.http.HttpMethod

class RemoteProductDataSource : RemoteBaseDataSource(
    collectionName = "api/products",
) {
    suspend fun readProduct(productId: String): Result<ProductResponse> = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Get,
    )

    suspend fun readUserProducts(): Result<List<ProductResponse>> = doRequest(
        method = HttpMethod.Get,
    )

    suspend fun addProductToUser(productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$productId/claim",
        method = HttpMethod.Post,
        requestBody = AddProductToUserRequest(productId = productId),
    )

    suspend fun deleteProductFromUser(productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$productId/abandon",
        method = HttpMethod.Post,
    )

    suspend fun update(
        productId: String,
        customLink: String? = null
    ): Result<Unit> = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Put,
        requestBody = UpdateUserProductRequest(customLink = customLink),
    )
}