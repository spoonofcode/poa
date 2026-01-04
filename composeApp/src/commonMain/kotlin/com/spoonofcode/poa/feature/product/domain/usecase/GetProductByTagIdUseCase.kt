package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product

class GetProductByTagIdUseCase(
    private val productDataSource: ProductDataSource,
) {
    suspend operator fun invoke(
        tagId: String,
    ): Result<Product> = productDataSource.readProductByTagId(tagId = tagId)
}