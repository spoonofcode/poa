package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class DeleteProductFromUserUseCase(
    private val productRepository: ProductRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        productId: String,
    ): Result<Unit> {
        return productRepository.deleteProductFromUser(
            productId = productId,
            userId = sessionManager.getSessionUserId(),
        )
    }
}