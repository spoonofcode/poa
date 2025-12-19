package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserDataSource
import com.spoonofcode.poa.core.network.SessionManager

class DeleteProductFromUserUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        productId: String,
    ): Result<Unit> {
        return userDataSource.deleteProductFromUser(
            productId = productId,
            userId = sessionManager.getSessionUserId(),
        )
    }
}