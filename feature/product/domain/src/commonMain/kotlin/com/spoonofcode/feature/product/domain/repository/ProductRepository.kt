package com.spoonofcode.feature.product.domain.repository

import com.spoonofcode.feature.product.domain.model.Product

interface ProductRepository {
    suspend fun getProduct(productId: String): Result<Product>
    suspend fun getProductByTagId(tagId: String): Result<Product>
    suspend fun getUserProducts(): Result<List<Product>>
    suspend fun getProductSeriesIds(): Result<List<String>>
    suspend fun addProductToUser(productId: String): Result<Unit>
    suspend fun deleteProductFromUser(productId: String): Result<Unit>
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
    ): Result<Unit>
}