package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.AddProductToUserRequest
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import io.ktor.http.HttpMethod

class RemoteUserProductDataSource : RemoteBaseDataSource(
    collectionName = "api/products",
) {
    suspend fun readUserProducts(): Result<List<ProductResponse>> = doRequest(
        method = HttpMethod.Get,
    )

    suspend fun readProductSeriesIds(): Result<List<String>> = doRequest(
        urlPostfixPath = "product-series-ids",
        method = HttpMethod.Get,
    )

    suspend fun addProductToUser(productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "products",
        method = HttpMethod.Post,
        requestBody = AddProductToUserRequest(productId = productId),
    )

    suspend fun deleteProductFromUser(productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "products/$productId",
        method = HttpMethod.Delete,
    )
}