package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus
import com.spoonofcode.poa.core.network.SessionManager

internal class GetUserDailyCheckInStatusUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(): Result<DailyCheckInStatus> {
        val userId = sessionManager.getSessionUserId()
        return userDataSource.getUserDailyCheckInStatus(userId)
    }
}