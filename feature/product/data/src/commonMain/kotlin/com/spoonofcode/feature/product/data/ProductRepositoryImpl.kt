package com.spoonofcode.feature.product.data

import com.spoonofcode.feature.product.data.mappers.toProduct
import com.spoonofcode.feature.product.data.remote.RemoteProductDataSource
import com.spoonofcode.feature.product.data.testdata.PRODUCTS
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remoteProducts: RemoteProductDataSource,
) : ProductRepository {

    override suspend fun getProduct(productId: String): Result<Product> =
        remoteProducts.readProduct(productId).map { it.toProduct() }

    override suspend fun getUserProducts(): Result<List<Product>> =
        remoteProducts.readUserProducts().map { list -> list.map { it.toProduct() } }

    override suspend fun getProductSeriesIds(): Result<List<String>> =
        remoteProducts.readProductSeriesIds()

    override suspend fun addProductToUser(productId: String): Result<Unit> =
        remoteProducts.addProductToUser(productId)

    override suspend fun deleteProductFromUser(productId: String): Result<Unit> =
        remoteProducts.deleteProductFromUser(productId)

    override suspend fun update(
        productId: String,
        customLink: String?,
    ): Result<Unit> =
        remoteProducts.update(
            productId = productId,
            customLink = customLink,
        )
}