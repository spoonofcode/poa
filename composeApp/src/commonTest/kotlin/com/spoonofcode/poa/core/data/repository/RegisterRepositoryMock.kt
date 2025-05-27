package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.RegisterMockData.REGISTER_1
import com.spoonofcode.poa.core.model.RegisterRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun registerRepositoryMock() = mock<RegisterRepository> {
    everySuspend { create(any<RegisterRequest>()) } returns REGISTER_1
    everySuspend { read(any()) } returns REGISTER_1
}