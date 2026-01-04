package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product

class GetProductByIdUseCase(
    private val productDataSource: ProductDataSource,
) {
    suspend operator fun invoke(
        productId: String
    ): Result<Product> = productDataSource.read(productId = productId)
}