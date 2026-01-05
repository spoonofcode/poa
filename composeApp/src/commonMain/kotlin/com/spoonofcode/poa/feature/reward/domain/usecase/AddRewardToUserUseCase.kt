package com.spoonofcode.poa.feature.reward.domain.usecase

import com.spoonofcode.poa.feature.reward.data.model.RewardType
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class AddRewardToUserUseCase(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(
        rewardType: RewardType
    ): Result<Unit> {
        return profileRepository.addRewardToUser(
            rewardType = rewardType,
            userId = sessionManager.getSessionUserId(),
        )
    }
}