package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginMockData.LOGIN_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginRepositoryMock() = mock<LoginRepository> {
    everySuspend { create(any(), any()) } returns Result.success(LOGIN_1)
}