package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

internal class GetUserRewardsPointsUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<Int> {
        val userId = sessionManager.getSessionUserId()
        return profileRepository.getUserRewardsPoints(userId)
    }
}