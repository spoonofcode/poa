package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.core.model.Partner
import com.spoonofcode.poa.core.model.Product

object ProductMockData {
    val PRODUCT_1 = Product(
        id = "1",
        name = "Product 1",
        description = "Description 1",
        seriesId = "1",
        collectionName = "Collection Name 1",
        imageLink = "Image Link 1",
        videoLink = "Video Link 1",
        tagId = "Tag Id 1",
        websiteLink = "Web Link 1",
        customLink = "Custom Link 1",
        ownerUser = null,
        partner = Partner(
            id = "1",
            name = "Partner Name 1",
            email = "partner1@gmail.com",
            description = "Partner Description 1",
            imageLink = "Partner Image Link 1",
            websiteLink = "Partner Web Link 1",
            instagramLink = "Partner Instagram Link 1",
            facebookLink = "Partner Facebook Link 1",
            youtubeLink = "Partner Youtube Link 1",
            xLink = "Partner X Link 1",
            userId = "1",
        )
    )

    val PRODUCT_2 = Product(
        id = "2",
        name = "Product 2",
        description = "Description 2",
        seriesId = "2",
        collectionName = "Collection Name 2",
        imageLink = "Image Link 2",
        videoLink = "Video Link 2",
        tagId = "Tag Id 2",
        websiteLink = "Web Link 2",
        customLink = "Custom Link 2",
        ownerUser = null,
        partner = Partner(
            id = "2",
            name = "Partner Name 2",
            email = "partner2@gmail.com",
            description = "Partner Description 2",
            imageLink = "Partner Image Link 2",
            websiteLink = "Partner Web Link 2",
            instagramLink = "Partner Instagram Link 2",
            facebookLink = "Partner Facebook Link 2",
            youtubeLink = "Partner Youtube Link 2",
            xLink = "Partner X Link 2",
            userId = "2",
        )
    )

    val PRODUCTS = listOf(PRODUCT_1, PRODUCT_2)
}