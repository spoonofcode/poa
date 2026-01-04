package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginMockData.LOGIN_1
import com.spoonofcode.poa.feature.login.data.remote.LoginDataSource
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginRepositoryMock() = mock<LoginDataSource> {
    everySuspend { create(any(), any()) } returns Result.success(LOGIN_1)
}