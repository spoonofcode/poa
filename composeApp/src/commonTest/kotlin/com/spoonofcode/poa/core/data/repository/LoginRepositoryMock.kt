package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginMockData.LOGIN_1
import com.spoonofcode.poa.core.model.LoginRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginRepositoryMock() = mock<LoginRepository> {
    everySuspend { create(any<LoginRequest>()) } returns LOGIN_1
    everySuspend { read(any()) } returns LOGIN_1
}