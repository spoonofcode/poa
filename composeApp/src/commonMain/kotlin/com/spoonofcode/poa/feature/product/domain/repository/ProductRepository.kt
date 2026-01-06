package com.spoonofcode.poa.feature.product.domain.repository

import com.spoonofcode.poa.feature.product.domain.model.Product

interface ProductRepository {
    suspend fun getProduct(productId: String): Result<Product>
    suspend fun getProductByTagId(tagId: String): Result<Product>
    suspend fun getUserProducts(userId: String): Result<List<Product>>
    suspend fun getProductSeriesIds(userId: String): Result<List<String>>
    suspend fun addProductToUser(userId: String, productId: String): Result<Unit>
    suspend fun deleteProductFromUser(userId: String, productId: String): Result<Unit>
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
}