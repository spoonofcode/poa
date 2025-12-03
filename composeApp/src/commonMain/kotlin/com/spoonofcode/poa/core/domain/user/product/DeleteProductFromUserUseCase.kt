package com.spoonofcode.poa.core.domain.user.product

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager

class DeleteProductFromUserUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        productId: String,
    ) {
        userRepository.deleteProductFromUser(
            productId = productId,
            userId = sessionManager.getSessionUserId(),
        )
    }
}