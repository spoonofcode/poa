package com.spoonofcode.poa.core.domain.user

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.network.SessionManager

internal class GetUserPointsUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Int {
        val userId = sessionManager.getSessionUserId()
        return userRepository.getUserPoints(userId)
    }
}