package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository

class DeleteProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(productId: Int): Boolean = productRepository.delete(id = productId)
}