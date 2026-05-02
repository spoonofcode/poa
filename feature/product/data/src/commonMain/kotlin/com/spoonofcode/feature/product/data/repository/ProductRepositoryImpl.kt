package com.spoonofcode.feature.product.data.repository

import com.spoonofcode.feature.product.data.mappers.toProduct
import com.spoonofcode.feature.product.data.remote.RemoteProductDataSource
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remoteProductDataSource: RemoteProductDataSource,
) : ProductRepository {

    override suspend fun getProduct(productId: String): Result<Product> =
        remoteProductDataSource.readProduct(productId).map { it.toProduct() }

    override suspend fun getUserProducts(): Result<List<Product>> =
        remoteProductDataSource.readUserProducts().map { list -> list.map { it.toProduct() } }

    override suspend fun addProductToUser(productId: String): Result<Unit> =
        remoteProductDataSource.addProductToUser(productId)

    override suspend fun deleteProductFromUser(productId: String): Result<Unit> =
        remoteProductDataSource.deleteProductFromUser(productId)

    override suspend fun update(
        productId: String,
        customLink: String?,
    ): Result<Unit> =
        remoteProductDataSource.update(
            productId = productId,
            customLink = customLink,
        )
}