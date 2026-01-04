package com.spoonofcode.poa.feature.reward.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyCheckInStatus(
    val streak: Int,
    val nextCheckInTimeMillis: Long,
    val dailyRewards: List<DailyReward>,
    val isAvailableNow: Boolean,
)