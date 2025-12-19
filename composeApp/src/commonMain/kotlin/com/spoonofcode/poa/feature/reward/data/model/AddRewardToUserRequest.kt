package com.spoonofcode.poa.feature.reward.data.model

import com.spoonofcode.poa.core.model.RewardType
import kotlinx.serialization.Serializable

@Serializable
data class AddRewardToUserRequest(
    val rewardType: RewardType,
)