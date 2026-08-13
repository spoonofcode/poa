package com.spoonofcode.feature.product.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.feature.product.domain.model.Product

fun ProductResponse.toProduct(): Product {
    return Product(
        id = id,
        createdAt = createdAt.toZonedLocalDateTime(),
        customLink = customLink,
        description = description,
        imageLink = imageLink,
        name = name,
        ownerEmail = ownerEmail,
        partnerId = partnerId,
        seriesId = seriesId,
        seriesName = seriesName,
        videoLink = videoLink,
    )
}