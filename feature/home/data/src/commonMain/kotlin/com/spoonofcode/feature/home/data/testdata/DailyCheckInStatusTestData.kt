package com.spoonofcode.feature.home.data.testdata

import com.spoonofcode.feature.home.domain.model.DailyCheckInStatus
import com.spoonofcode.feature.home.domain.model.DailyReward
import com.spoonofcode.feature.home.domain.model.DailyRewardStatus

val DAILY_CHECK_IN_STATUS = DailyCheckInStatus(
    streak = 0,
    nextCheckInTimeMillis = 0L,
    isAvailableNow = true,
    dailyRewards = listOf(
        DailyReward(day = 1, points = 10, status = DailyRewardStatus.AVAILABLE_TO_CLAIM),
        DailyReward(day = 2, points = 20, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 3, points = 30, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 4, points = 40, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 5, points = 50, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 6, points = 60, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 7, points = 70, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 8, points = 80, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 9, points = 90, status = DailyRewardStatus.LOCKED),
        DailyReward(day = 10, points = 100, status = DailyRewardStatus.LOCKED),
    )
)

