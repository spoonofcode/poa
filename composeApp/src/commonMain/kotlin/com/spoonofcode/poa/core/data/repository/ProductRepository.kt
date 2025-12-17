package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.RemoteBaseDataSource
import com.spoonofcode.poa.core.data.model.ProductRequest
import com.spoonofcode.poa.core.model.Product
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

interface ProductRepository {
    suspend fun read(
        productId: String,
    ): Result<Product>

    suspend fun update(
        productId: String,
        ownerUserId: String? = null,
        partnerId: String,
        name: String,
        description: String,
        seriesId: String,
        collectionName: String,
        imageLink: String,
        videoLink: String? = null,
        tagId: String? = null,
        websiteLink: String? = null,
        customLink: String? = null,
    ): Result<Unit>

    suspend fun readProductByTagId(tagId: String): Result<Product>
}

class ProductRemoteDataSource : RemoteBaseDataSource(
    collectionName = "products",
), ProductRepository {
    override suspend fun read(
        productId: String,
    ): Result<Product> = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Product>()
    )

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

    override suspend fun readProductByTagId(tagId: String): Result<Product> = doRequest(
        method = HttpMethod.Get,
        queryParams = mapOf("tagId" to tagId),
        responseTypeInfo = typeInfo<Product>()
    )
}