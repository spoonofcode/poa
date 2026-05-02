package com.spoonofcode.feature.home.domain.model

data class DailyCheckInStatus(
    val streak: Int,
    val nextCheckInTimeMillis: Long,
    val dailyRewards: List<DailyReward>,
    val isAvailableNow: Boolean,
)