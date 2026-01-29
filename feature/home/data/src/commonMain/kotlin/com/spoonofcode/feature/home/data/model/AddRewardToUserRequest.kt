package com.spoonofcode.feature.home.data.model

import com.spoonofcode.feature.home.domain.model.RewardType
import kotlinx.serialization.Serializable

@Serializable
data class AddRewardToUserRequest(
    val rewardType: RewardType,
)