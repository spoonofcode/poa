package com.spoonofcode.feature.profile.data

class ProductRepositoryImpl(
    private val remoteProducts: RemoteProductDataSource,
    private val remoteUserProducts: RemoteUserProductDataSource,
) : ProductRepository {

    // TODO #99 Adopt poa backend
    override suspend fun getProduct(productId: String): Result<Product> =
        Result.success(PRODUCTS.first())
//        remoteProducts.read(productId).map { it.toProduct() }

    // TODO #99 Adopt poa backend
    override suspend fun getProductByTagId(tagId: String): Result<Product> =
        Result.success(PRODUCTS.last())
//        remoteProducts.readProductByTagId(tagId).map { it.toProduct() }

    // TODO #99 Adopt poa backend
    override suspend fun getUserProducts(): Result<List<Product>> =
        Result.success(PRODUCTS)
//        remoteUserProducts.readUserProducts().map { list -> list.map { it.toProduct() } }

    override suspend fun getProductSeriesIds(): Result<List<String>> =
        remoteUserProducts.readProductSeriesIds()

    override suspend fun addProductToUser(productId: String): Result<Unit> =
        remoteUserProducts.addProductToUser(productId)

    override suspend fun deleteProductFromUser(productId: String): Result<Unit> =
        remoteUserProducts.deleteProductFromUser(productId)

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