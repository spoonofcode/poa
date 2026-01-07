package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        productId: String
    ): Result<Product> = productRepository.getProduct(productId = productId)
}