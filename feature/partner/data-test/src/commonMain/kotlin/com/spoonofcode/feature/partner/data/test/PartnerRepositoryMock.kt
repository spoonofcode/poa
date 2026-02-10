package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.data.test.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNERS
import com.spoonofcode.feature.partner.data.test.PartnerMockData.PARTNER_1
import com.spoonofcode.feature.partner.data.test.PartnerUserMockData.PARTNER_USERS
import com.spoonofcode.feature.partner.domain.repository.PartnerRepository
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