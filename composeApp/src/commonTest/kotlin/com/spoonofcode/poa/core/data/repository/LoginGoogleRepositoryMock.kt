package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.LoginGoogleMockData.LOGIN_GOOGLE_1
import com.spoonofcode.poa.core.model.LoginGoogleRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun loginGoogleRepositoryMock() = mock<LoginGoogleRepository> {
    everySuspend { create(any<LoginGoogleRequest>()) } returns LOGIN_GOOGLE_1
    everySuspend { read(any()) } returns LOGIN_GOOGLE_1
}