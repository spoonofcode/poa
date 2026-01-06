package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNERS
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun partnerRepositoryMock() = mock<PartnerDataSource> {
    everySuspend { read(any()) } returns Result.success(PARTNER_1)
    everySuspend { update(any(), any()) } returns Result.success(true)
    everySuspend { readAll() } returns Result.success(PARTNERS)
    everySuspend { readPartnerUsers(any()) } returns Result.success(USERS)
}