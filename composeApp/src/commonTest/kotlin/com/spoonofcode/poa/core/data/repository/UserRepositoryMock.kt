package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.DailyCheckInStatusMockData.DAILY_CHECK_IN_STATUS_1
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNERS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

val TOTAL_POINTS = 1000

internal fun userRepositoryMock() = mock<UserDataSource> {
    everySuspend { read(any()) } returns Result.success(USER_1)
    everySuspend { readUserProfile(any()) } returns Result.success(PROFILE_1)
    everySuspend { addProductToUser(any(), any()) } returns Result.success(Unit)
    everySuspend { deleteProductFromUser(any(), any()) } returns Result.success(Unit)
    everySuspend { addRewardToUser(any(), any()) } returns Result.success(Unit)
    everySuspend { getUserDailyCheckInStatus(any()) } returns Result.success(DAILY_CHECK_IN_STATUS_1)
    everySuspend { getUserRewardsPoints(any()) } returns Result.success(TOTAL_POINTS)
    everySuspend { readUserPartners(any()) } returns Result.success(PARTNERS)
    everySuspend { readUserProducts(any()) } returns Result.success(PRODUCTS)
}