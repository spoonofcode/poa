package com.spoonofcode.poa.feature.product.data.remote

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.feature.product.data.remote.model.ProductRequest
import com.spoonofcode.poa.feature.product.data.mappers.toProduct
import com.spoonofcode.poa.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.usecase.ProductDataSource
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

class RemoteProductDataSource : RemoteBaseDataSource(
    collectionName = "products",
), ProductDataSource {
    override suspend fun read(
        productId: String,
    ): Result<Product> = doRequest<ProductResponse>(
        urlPostfixPath = productId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<ProductResponse>()
    ).map { response -> response.toProduct() }

    override suspend fun update(
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
        responseTypeInfo = typeInfo<Unit>()
    )

    override suspend fun readProductByTagId(tagId: String): Result<Product> =
        doRequest<ProductResponse>(
            method = HttpMethod.Get,
            queryParams = mapOf("tagId" to tagId),
            responseTypeInfo = typeInfo<ProductResponse>()
        ).map { response -> response.toProduct() }
}