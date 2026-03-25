package com.spoonofcode.feature.product.domain.repository

import com.spoonofcode.feature.product.domain.model.Product

interface ProductRepository {
    suspend fun getProduct(productId: String): Result<Product>
    suspend fun getUserProducts(): Result<List<Product>>
    suspend fun getProductSeriesIds(): Result<List<String>>
    suspend fun addProductToUser(productId: String): Result<Unit>
    suspend fun deleteProductFromUser(productId: String): Result<Unit>
    suspend fun update(
        productId: String,
        customLink: String? = null
    ): Result<Unit>
}