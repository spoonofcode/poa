package com.spoonofcode.feature.product.data.mappers

import com.spoonofcode.feature.product.data.remote.model.ProductTypeResponse
import com.spoonofcode.feature.product.domain.model.ProductType

internal fun ProductTypeResponse.toProductType(): ProductType = when (this) {
    ProductTypeResponse.ITEM -> ProductType.ITEM
    ProductTypeResponse.REDIRECT -> ProductType.REDIRECT
}