package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginGoogleMockData.LOGIN_GOOGLE_1
import com.spoonofcode.poa.feature.login.data.remote.LoginGoogleDataSource
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginGoogleRepositoryMock() = mock<LoginGoogleDataSource> {
    everySuspend { create(any()) } returns Result.success(LOGIN_GOOGLE_1)
}