package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.feature.reward.data.model.DailyCheckInStatus

object DailyCheckInStatusMockData {
    val DAILY_CHECK_IN_STATUS_1 = DailyCheckInStatus(
        streak = 1,
        nextCheckInTimeMillis = 1762124400000,
        dailyRewards = listOf(),
        isAvailableNow = true,
    )
}