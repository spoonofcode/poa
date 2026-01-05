package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class GetProductByTagIdUseCase(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(
        tagId: String,
    ): Result<Product> = productRepository.getProductByTagId(tagId = tagId)
}