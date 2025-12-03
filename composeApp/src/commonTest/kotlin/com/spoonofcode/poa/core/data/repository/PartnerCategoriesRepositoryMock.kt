package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.PartnerCategoriesMockData.PARTNER_CATEGORIES
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun partnerCategoriesRepositoryMock() = mock<PartnerCategoryRepository> {
    everySuspend { readAll() } returns PARTNER_CATEGORIES
}