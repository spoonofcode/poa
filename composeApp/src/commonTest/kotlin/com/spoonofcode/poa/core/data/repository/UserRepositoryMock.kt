package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

val TOTAL_POINTS = 1000

internal fun userRepositoryMock() = mock<UserRepository> {
    everySuspend { create(any()) } returns USER_1
    everySuspend { read(any()) } returns USER_1
    everySuspend { readAll() } returns USERS
    everySuspend { readUserProfile(any()) } returns PROFILE_1
    everySuspend { addRewardToUser(any(), any()) } returns Unit
    everySuspend { getUserDailyCheckInStatus(any()) } returns DAILY_CHECK_IN_STATUS_1
    everySuspend { getUserRewardsPoints(any()) } returns TOTAL_POINTS
    everySuspend { readUserPartners(any()) } returns listOf(PARTNER_1)
}