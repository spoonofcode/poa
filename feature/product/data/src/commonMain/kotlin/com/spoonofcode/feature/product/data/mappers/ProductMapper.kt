package com.spoonofcode.feature.profile.data.mappers

import com.spoonofcode.feature.profile.data.remote.model.ProductResponse
import com.spoonofcode.poa.feature.product.domain.model.Product

fun ProductResponse.toProduct(): Product {
    return Product(
        id = id,
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