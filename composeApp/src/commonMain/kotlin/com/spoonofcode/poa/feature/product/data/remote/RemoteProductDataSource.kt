package com.spoonofcode.poa.feature.product.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.product.data.remote.model.ProductRequest
import com.spoonofcode.poa.feature.product.data.remote.model.ProductResponse
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
        ownerUserId: String?,
        partnerId: String,
        name: String,
        description: String,
        seriesId: String,
        collectionName: String,
        imageLink: String,
        videoLink: String?,
        tagId: String?,
        websiteLink: String?,
        customLink: String?,
    ): Result<Unit> = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Put,
        requestBody = ProductRequest(
            ownerUserId = ownerUserId,
            partnerId = partnerId,
            name = name,
            description = description,
            seriesId = seriesId,
            collectionName = collectionName,
            imageLink = imageLink,
            videoLink = videoLink,
            tagId = tagId,
            websiteLink = websiteLink,
            customLink = customLink,
        ),
    )

    suspend fun readProductByTagId(tagId: String): Result<ProductResponse> =
        doRequest<ProductResponse>(
            method = HttpMethod.Get,
            queryParams = mapOf("tagId" to tagId),
        )
}