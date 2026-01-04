package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.RegisterMockData.REGISTER_1
import com.spoonofcode.poa.feature.login.data.remote.RegisterDataSource
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun registerRepositoryMock() = mock<RegisterDataSource> {
    everySuspend { create(any(), any(), any(), any()) } returns Result.success(REGISTER_1)
}