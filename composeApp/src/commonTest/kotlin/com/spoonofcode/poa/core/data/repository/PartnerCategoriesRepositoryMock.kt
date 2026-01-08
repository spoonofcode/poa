package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import com.spoonofcode.poa.feature.partner.domain.repository.PartnerRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun partnerCategoriesRepositoryMock() = mock<PartnerRepository> {
    everySuspend { getCategories() } returns Result.success(PARTNER_CATEGORIES)
}