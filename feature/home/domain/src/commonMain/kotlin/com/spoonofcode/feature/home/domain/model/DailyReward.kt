package com.spoonofcode.feature.home.domain.model


enum class DailyRewardStatus {
    CLAIMED,
    AVAILABLE_TO_CLAIM,
    LOCKED
}

data class DailyReward(
    val day: Int,
    val points: Int,
    val status: DailyRewardStatus,
)