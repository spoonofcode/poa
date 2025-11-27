package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyCheckInStatus(
    val streak: Int,
    val nextCheckInTimeMillis: Long,
    val dailyRewards: List<DailyReward>,
)