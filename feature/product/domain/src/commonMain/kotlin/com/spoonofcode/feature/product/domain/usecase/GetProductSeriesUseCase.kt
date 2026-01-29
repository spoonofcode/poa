package com.spoonofcode.feature.product.domain.usecase

import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.feature.product.domain.repository.ProductRepository

class GetProductSeriesUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return Result.success(emptyList())
//        return productRepository.getProductSeriesIds()
    }
}