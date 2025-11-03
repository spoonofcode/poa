package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.ProductRequest

class EditProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(productId: String, productRequest: ProductRequest): Boolean =
        productRepository.update(
            id = productId,
            request = productRequest,
        )
}