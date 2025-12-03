package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.poa.core.model.PartnerRequest
import com.spoonofcode.poa.core.model.ProfileRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun partnerRepositoryMock() = mock<PartnerRepository> {
    everySuspend { create(any()) } returns PARTNER_1
    everySuspend { read(any()) } returns PARTNER_1
    everySuspend { readPartnerUsers(any()) } returns USERS
}