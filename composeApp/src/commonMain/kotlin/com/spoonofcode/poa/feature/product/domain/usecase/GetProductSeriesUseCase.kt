package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.feature.product.domain.repository.ProductRepository

class GetProductSeriesUseCase(
    private val productRepository: ProductRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return Result.success(emptyList())
//        return productRepository.getProductSeriesIds(
//            userId = sessionManager.getSessionUserId(),
//        )
    }
}