package com.spoonofcode.poa.feature.product.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.product.data.remote.model.AddProductToUserRequest
import com.spoonofcode.poa.feature.product.data.remote.model.ProductResponse
import io.ktor.http.HttpMethod

class RemoteUserProductDataSource : RemoteBaseDataSource(
    collectionName = "users",
) {
    suspend fun readUserProducts(userId: String): Result<List<ProductResponse>> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Get,
    )

    suspend fun readProductSeriesIds(userId: String): Result<List<String>> = doRequest(
        urlPostfixPath = "$userId/product-series-ids",
        method = HttpMethod.Get,
    )

    suspend fun addProductToUser(userId: String, productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/products",
        method = HttpMethod.Post,
        requestBody = AddProductToUserRequest(productId = productId),
    )

    suspend fun deleteProductFromUser(userId: String, productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "$userId/products/$productId",
        method = HttpMethod.Delete,
    )
}