package com.spoonofcode.poa.core.domain.product

import com.spoonofcode.poa.core.data.repository.ProductRepository
import com.spoonofcode.poa.core.model.Product
import kotlin.time.ExperimentalTime

class GetProductByIdUseCase(
    private val productRepository: ProductRepository,
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        productId: String
    ): Product = productRepository.read(productId = productId)
}