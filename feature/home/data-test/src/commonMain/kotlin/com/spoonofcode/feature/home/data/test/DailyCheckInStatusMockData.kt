package com.spoonofcode.feature.home.data.test

import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus

object DailyCheckInStatusMockData {
    val DAILY_CHECK_IN_STATUS_1 = DailyCheckInStatus(
        streak = 1,
        nextCheckInTimeMillis = 1762124400000,
        dailyRewards = listOf(),
        isAvailableNow = true,
    )
}