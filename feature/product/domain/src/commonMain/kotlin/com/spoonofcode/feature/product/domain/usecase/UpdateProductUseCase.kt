package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.repository.ProductRepository

class UpdateProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        id: String,
        customLink: String? = null
    ): Result<Unit> =
        productRepository.update(
            productId = id,
            customLink = customLink,
        )
}