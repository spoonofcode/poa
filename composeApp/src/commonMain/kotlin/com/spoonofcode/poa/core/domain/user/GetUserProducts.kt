package com.spoonofcode.poa.core.domain.user

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager

class GetUserProducts(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() {
        userRepository.readUserProducts(
            userId = sessionManager.getSessionUserId(),
        )
    }
}