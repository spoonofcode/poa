package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product

class GetProductSeriesUseCase(
    private val productDataSource: ProductDataSource,
) {
    //TODO Fix this use case
    suspend operator fun invoke(): Result<List<Product>> {
        return Result.success(emptyList())
//        return productRepository.readAll().distinctBy { product -> product.seriesId }
    }
}

