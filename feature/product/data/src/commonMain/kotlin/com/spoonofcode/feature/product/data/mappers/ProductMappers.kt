package com.spoonofcode.feature.product.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.product.data.remote.model.ProductResponse
import com.spoonofcode.feature.product.domain.model.Product

internal fun ProductResponse.toProduct(): Product = Product(
    id = id,
    productType = productType.toProductType(),
    ownerEmail = ownerEmail,
    partnerId = partnerId,
    name = name,
    description = description,
    seriesId = seriesId,
    seriesName = seriesName,
    createdAt = createdAt.toZonedLocalDateTime(),
    imageLink = imageLink,
    videoLink = videoLink,
    customLink = customLink,
    redirectLink = redirectLink,
)