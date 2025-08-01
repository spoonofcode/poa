package com.spoonofcode.poa.core.data.repository

import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

internal fun productFilterRepositoryMock() = mock<ProductFilterRepository>(MockMode.autoUnit) {
    every { getSelectedFilters() } returns ProductFilterData()
}