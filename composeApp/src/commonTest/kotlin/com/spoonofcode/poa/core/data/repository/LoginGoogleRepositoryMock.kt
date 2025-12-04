package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginGoogleMockData.LOGIN_GOOGLE_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginGoogleRepositoryMock() = mock<LoginGoogleRepository> {
    everySuspend { create(any()) } returns Result.success(LOGIN_GOOGLE_1)
}