package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product

class GetProductSeriesUseCase(
    private val productRepository: ProductRepository,
) {
    //TODO Fix this use case
    suspend operator fun invoke(): Result<List<Product>> {
        return Result.success(emptyList())
//        return productRepository.readAll().distinctBy { product -> product.seriesId }
    }
}

