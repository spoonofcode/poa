package com.spoonofcode.feature.product.data.remote

import com.spoonofcode.core.data.base.RemoteBaseDataSource
import com.spoonofcode.feature.product.data.remote.model.ProductRequest
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import io.ktor.http.HttpMethod

class RemoteProductDataSource : RemoteBaseDataSource(
    collectionName = "products",
) {
    suspend fun read(
        productId: String,
    ): Result<ProductResponse> = doRequest<ProductResponse>(
        urlPostfixPath = productId,
        method = HttpMethod.Get,
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

    suspend fun readProductByTagId(tagId: String): Result<ProductResponse> =
        doRequest<ProductResponse>(
            method = HttpMethod.Get,
            queryParams = mapOf("tagId" to tagId),
        )
}