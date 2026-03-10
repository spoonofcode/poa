package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.AddProductToUserRequest
import com.spoonofcode.feature.product.data.remote.model.ProductRequest
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
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

    suspend fun readProductSeriesIds(): Result<List<String>> = doRequest(
        urlPostfixPath = "product-series-ids",
        method = HttpMethod.Get,
    )

    suspend fun addProductToUser(productId: String): Result<Unit> = doRequest(
        method = HttpMethod.Post,
        requestBody = AddProductToUserRequest(productId = productId),
    )

    suspend fun deleteProductFromUser(productId: String): Result<Unit> = doRequest(
        urlPostfixPath = "products/$productId",
        method = HttpMethod.Delete,
    )

    suspend fun update(
        productId: String,
        partnerId: String? = null,
        name: String? = null,
        description: String? = null,
        seriesId: String? = null,
        seriesName: String? = null,
        imageLink: String? = null,
        videoLink: String? = null,
        customLink: String? = null,
    ): Result<Unit> = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Put,
        requestBody = ProductRequest(
            partnerId = partnerId,
            name = name,
            description = description,
            seriesId = seriesId,
            seriesName = seriesName,
            imageLink = imageLink,
            videoLink = videoLink,
            customLink = customLink,
        ),
    )
}