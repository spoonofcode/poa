package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class GetUserProductsUseCase(
    private val productRepository: ProductRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Product>> = productRepository.getUserProducts(
        userId = sessionManager.getSessionUserId(),
    )
}