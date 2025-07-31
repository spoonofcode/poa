package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterData
import com.spoonofcode.poa.core.data.repository.old.NFTInfoFilterRepository
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

internal fun filterRepositoryMock() = mock<NFTInfoFilterRepository>(MockMode.autoUnit) {
    every { getSelectedFilters() } returns NFTInfoFilterData()
}