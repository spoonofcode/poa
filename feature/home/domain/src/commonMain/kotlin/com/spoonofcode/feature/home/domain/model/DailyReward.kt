package com.spoonofcode.feature.home.domain.model

import kotlinx.serialization.Serializable


enum class DailyRewardStatus {
    CLAIMED,
    AVAILABLE_TO_CLAIM,
    LOCKED
}

@Serializable
data class DailyReward(
    val day: Int,
    val points: Int,
    val status: DailyRewardStatus,
)