package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product

class GetAllProductSeriesUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.readAll().distinctBy { product -> product.seriesId }
    }
}

