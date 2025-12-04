package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.base.BaseRepository
import com.spoonofcode.poa.core.data.model.ProductRequest
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.test.OpenForMokkery
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.typeInfo

@OpenForMokkery
class ProductRepository : BaseRepository(
    collectionName = "products",
) {
    suspend fun read(
        productId: String,
    ): Product = doRequest(
        urlPostfixPath = productId,
        method = HttpMethod.Get,
        responseTypeInfo = typeInfo<Product>()
    )

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
    ): Boolean {
        doRequest<Unit>(
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
        return true
    }

    suspend fun readProductByTagId(tagId: String): Product = doRequest(
        method = HttpMethod.Get,
        queryParams = mapOf("tagId" to tagId),
        responseTypeInfo = typeInfo<Product>()
    )
}