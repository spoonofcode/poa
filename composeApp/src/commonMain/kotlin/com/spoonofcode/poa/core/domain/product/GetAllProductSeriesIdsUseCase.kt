package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository

class GetAllProductSeriesIdsUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): List<String> {
        return productRepository.readAll().map { product -> product.seriesId }.distinct()
    }
}

