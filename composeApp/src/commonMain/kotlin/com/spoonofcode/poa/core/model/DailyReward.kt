package com.spoonofcode.poa.core.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
enum class DailyRewardStatus {
    CLAIMED,
    AVAILABLE_TO_CLAIM,
    LOCKED
}

@Serializable
@Immutable
data class DailyReward(
    val day: Int,
    val points: Int,
    val status: DailyRewardStatus,
)