package com.spoonofcode.feature.product.data.test

import com.spoonofcode.feature.product.domain.model.Product

object ProductMockData {
    val PRODUCT_1 = Product(
        id = "1",
        ownerUserId = "1",
        partnerId = "1",
        name = "Product 1",
        description = "Description 1",
        seriesId = "1",
        collectionName = "Collection Name 1",
        imageLink = "Image Link 1",
        videoLink = "Video Link 1",
        tagId = "Tag Id 1",
        websiteLink = "Web Link 1",
        customLink = "Custom Link 1",
    )

    val PRODUCT_2 = Product(
        id = "2",
        ownerUserId = "2",
        partnerId = "2",
        name = "Product 2",
        description = "Description 2",
        seriesId = "2",
        collectionName = "Collection Name 2",
        imageLink = "Image Link 2",
        videoLink = "Video Link 2",
        tagId = "Tag Id 2",
        websiteLink = "Web Link 2",
        customLink = "Custom Link 2",
    )

    val PRODUCTS = listOf(PRODUCT_1, PRODUCT_2)
}