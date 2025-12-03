package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNERS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

val TOTAL_POINTS = 1000

internal fun userRepositoryMock() = mock<UserRepository> {
    everySuspend { read(any()) } returns USER_1
    everySuspend { readUserProfile(any()) } returns PROFILE_1
    everySuspend { addProductToUser(any(), any()) } returns Unit
    everySuspend { deleteProductFromUser(any(), any()) } returns Unit
    everySuspend { addRewardToUser(any(), any()) } returns Unit
    everySuspend { getUserDailyCheckInStatus(any()) } returns DAILY_CHECK_IN_STATUS_1
    everySuspend { getUserRewardsPoints(any()) } returns TOTAL_POINTS
    everySuspend { readUserPartners(any()) } returns PARTNERS
    everySuspend { readUserProducts(any()) } returns PRODUCTS
}