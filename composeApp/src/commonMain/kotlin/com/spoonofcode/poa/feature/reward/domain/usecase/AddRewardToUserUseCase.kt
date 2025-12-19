package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserDataSource
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.network.SessionManager

class AddRewardToUserUseCase(
    private val userDataSource: UserDataSource,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        rewardType: RewardType
    ): Result<Unit> {
        return userDataSource.addRewardToUser(
            rewardType = rewardType,
            userId = sessionManager.getSessionUserId(),
        )
    }
}