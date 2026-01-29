package com.spoonofcode.feature.home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddRewardToUserRequest(
    val rewardType: RewardType,
)