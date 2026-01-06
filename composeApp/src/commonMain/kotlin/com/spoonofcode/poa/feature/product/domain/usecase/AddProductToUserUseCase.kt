package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class AddProductToUserUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        productId: String,
    ): Result<Unit> {
        return productRepository.addProductToUser(
            productId = productId,
        )
    }
}