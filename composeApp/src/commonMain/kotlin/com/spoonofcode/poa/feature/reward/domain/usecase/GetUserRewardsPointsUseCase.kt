package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.network.SessionManager

internal class GetUserRewardsPointsUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<Int> {
        val userId = sessionManager.getSessionUserId()
        return userDataSource.getUserRewardsPoints(userId)
    }
}