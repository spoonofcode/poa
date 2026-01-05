package com.spoonofcode.poa.feature.product.data

import com.spoonofcode.poa.feature.product.data.mappers.toProduct
import com.spoonofcode.poa.feature.product.data.remote.RemoteProductDataSource
import com.spoonofcode.poa.feature.product.data.remote.RemoteUserProductDataSource
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remoteProducts: RemoteProductDataSource,
    private val remoteUserProducts: RemoteUserProductDataSource,
) : ProductRepository {

    override suspend fun getProduct(productId: String): Result<Product> =
        remoteProducts.read(productId).map { it.toProduct() }

    override suspend fun getProductByTagId(tagId: String): Result<Product> =
        remoteProducts.readProductByTagId(tagId).map { it.toProduct() }

    override suspend fun getUserProducts(userId: String): Result<List<Product>> =
        remoteUserProducts.readUserProducts(userId).map { list -> list.map { it.toProduct() } }

    override suspend fun getProductSeriesIds(userId: String): Result<List<String>> =
        remoteUserProducts.readProductSeriesIds(userId)

    override suspend fun addProductToUser(userId: String, productId: String): Result<Unit> =
        remoteUserProducts.addProductToUser(userId, productId)

    override suspend fun deleteProductFromUser(userId: String, productId: String): Result<Unit> =
        remoteUserProducts.deleteProductFromUser(userId, productId)

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
    ): Result<Unit> = remoteProducts.update(
        productId = productId,
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
    )
}