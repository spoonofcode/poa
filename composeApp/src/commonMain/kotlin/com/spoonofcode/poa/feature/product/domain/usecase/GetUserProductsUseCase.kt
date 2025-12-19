package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.network.SessionManager

class GetUserProductsUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Product>> = userRepository.readUserProducts(
        userId = sessionManager.getSessionUserId(),
    )
}