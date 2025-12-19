package com.spoonofcode.poa.feature.product.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserDataSource
import com.spoonofcode.poa.core.network.SessionManager

class AddProductToUserUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        productId: String,
    ): Result<Unit> {
        return userDataSource.addProductToUser(
            productId = productId,
            userId = sessionManager.getSessionUserId(),
        )
    }
}