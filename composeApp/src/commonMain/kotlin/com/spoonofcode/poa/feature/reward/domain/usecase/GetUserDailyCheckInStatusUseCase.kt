package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.DailyCheckInStatus
import com.spoonofcode.poa.core.network.SessionManager

internal class GetUserDailyCheckInStatusUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> {
        val userId = sessionManager.getSessionUserId()
        return userRepository.getUserDailyCheckInStatus(userId)
    }
}