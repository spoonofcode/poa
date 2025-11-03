package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.poa.core.model.UserRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun userRepositoryMock() = mock<UserRepository> {
    everySuspend { create(any<UserRequest>()) } returns USER_1
    everySuspend { read(any()) } returns USER_1
    everySuspend { readAll() } returns USERS
}