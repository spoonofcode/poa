package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.feature.profile.domain.UserDataSource
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.network.SessionManager

class GetUserProductsUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<List<Product>> = userDataSource.readUserProducts(
        userId = sessionManager.getSessionUserId(),
    )
}