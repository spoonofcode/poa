package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNERS
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun partnerRepositoryMock() = mock<PartnerRepository> {
    everySuspend { read(any()) } returns Result.success(PARTNER_1)
    everySuspend { update(any(), any()) } returns Result.success(true)
    everySuspend { readAll() } returns Result.success(PARTNERS)
    everySuspend { readPartnerUsers(any()) } returns Result.success(USERS)
}