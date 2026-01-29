package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.repository.ProductRepository

class AddProductToUserUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        productId: String,
    ): Result<Unit> = productRepository.addProductToUser(
        productId = productId,
    )
}