package com.spoonofcode.poa.core.domain.user.reward

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.RewardType
import com.spoonofcode.poa.core.network.SessionManager

class AddRewardToUserUseCase(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        rewardType: RewardType
    ): Result<Unit> {
        return userRepository.addRewardToUser(
            rewardType = rewardType,
            userId = sessionManager.getSessionUserId(),
        )
    }
}