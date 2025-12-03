package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product

class GetProductSeriesUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): List<Product> {
        return emptyList()
        // TOOD Fix this
//        return productRepository.readAll().distinctBy { product -> product.seriesId }
    }
}

