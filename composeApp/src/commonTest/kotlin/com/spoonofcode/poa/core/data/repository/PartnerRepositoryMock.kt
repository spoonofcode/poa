package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.poa.core.data.mockdata.PartnerMockData.PARTNERS
import com.spoonofcode.poa.core.data.mockdata.PartnerUserMockData.PARTNER_USERS
import com.spoonofcode.poa.feature.partner.data.mock.PartnerMockData.PARTNER_1
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun partnerRepositoryMock() = mock<PartnerRepository> {
    everySuspend { getPartner(any()) } returns Result.success(PARTNER_1)
    everySuspend { updatePartner(any(), any()) } returns Result.success(true)
    everySuspend { getPartners() } returns Result.success(PARTNERS)
    everySuspend { getPartnerUsers(any()) } returns Result.success(PARTNER_USERS)
    everySuspend { getCategories() } returns Result.success(PARTNER_CATEGORIES)
}