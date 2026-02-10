package com.spoonofcode.feature.home.data.test

import com.spoonofcode.feature.home.data.test.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.feature.home.domain.repository.RewardRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

val TOTAL_POINTS = 1000

internal fun userRepositoryMock() = mock<RewardRepository> {
    everySuspend { addRewardToUser(any()) } returns Result.success(Unit)
    everySuspend { getUserDailyCheckInStatus() } returns Result.success(DAILY_CHECK_IN_STATUS_1)
    everySuspend { getUserRewardsPoints() } returns Result.success(TOTAL_POINTS)
}