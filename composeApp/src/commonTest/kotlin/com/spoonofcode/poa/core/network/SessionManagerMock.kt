package com.spoonofcode.poa.core.network

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionManagerMock() = mock<SessionManager>()
    .apply {
        every { getSessionUserId() } returns 1
    }