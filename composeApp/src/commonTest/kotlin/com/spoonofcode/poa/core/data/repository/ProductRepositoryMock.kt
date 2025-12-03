package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun productRepositoryMock() = mock<ProductRepository> {
    everySuspend { read(any()) } returns PRODUCT_1
    everySuspend { update(any(), any()) } returns true
    everySuspend { readProductByTagId(any()) } returns PRODUCT_1
}