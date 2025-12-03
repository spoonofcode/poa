package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCTS
import com.spoonofcode.poa.core.data.mockdata.ProductMockData.PRODUCT_1
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.poa.core.model.ProductRequest
import com.spoonofcode.poa.core.model.UserRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun productRepositoryMock() = mock<ProductRepository> {
    everySuspend { create(any()) } returns PRODUCT_1
    everySuspend { read(any()) } returns PRODUCT_1
    everySuspend { update(any(), any()) } returns true
    everySuspend { readAll() } returns PRODUCTS
    everySuspend { readProductByTagId(any()) } returns PRODUCT_1
}