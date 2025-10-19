package com.spoonofcode.poa.core.model

import androidx.compose.runtime.Immutable

enum class RewardStatus {
    CLAIMED,
    AVAILABLE_TO_CLAIM,
    LOCKED
}

@Immutable
data class DailyReward(
    val day: Int,
    val points: Int,
    val status: RewardStatus,
)