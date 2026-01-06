package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class GetUserProductsUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Result<List<Product>> = productRepository.getUserProducts()
}