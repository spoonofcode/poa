package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.poa.core.model.DailyReward
import com.spoonofcode.poa.core.model.RewardStatus

object RewardsMockData {
    fun getRewards(claimedDaysCount: Int): List<DailyReward> {
        val basePoints = 10
        val streakBonusPerDay = 5
        val weeklyBonus = 50

        return (1..16).map { day ->
            var points = basePoints + ((day - 1) * streakBonusPerDay)
            if (day > 0 && day % 7 == 0) {
                points += weeklyBonus
            }

            val status = when {
                day <= claimedDaysCount -> RewardStatus.CLAIMED
                day == claimedDaysCount + 1 -> RewardStatus.AVAILABLE_TO_CLAIM
                else -> RewardStatus.LOCKED
            }
            DailyReward(day, points, status)
        }
    }
}