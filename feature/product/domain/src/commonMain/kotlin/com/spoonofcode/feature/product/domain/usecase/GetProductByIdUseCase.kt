package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        productId: String,
    ): Result<Product> = productRepository.getProduct(productId = productId)
}
