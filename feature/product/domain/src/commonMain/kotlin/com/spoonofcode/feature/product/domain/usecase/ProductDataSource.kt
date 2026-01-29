package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.Product

interface ProductDataSource {
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