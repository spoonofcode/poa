package com.spoonofcode.poa.core.network

import com.spoonofcode.poa.core.data.mockdata.UserMockData.USER_1
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionManagerMock() = mock<SessionManager>(mode = MockMode.autoUnit) {
    every { getSessionUserId() } returns USER_1.id
}