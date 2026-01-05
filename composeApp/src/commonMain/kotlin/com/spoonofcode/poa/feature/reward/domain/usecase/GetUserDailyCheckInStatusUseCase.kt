package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus

internal class GetUserDailyCheckInStatusUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> {
        val userId = sessionManager.getSessionUserId()
        return profileRepository.getUserDailyCheckInStatus(userId)
    }
}