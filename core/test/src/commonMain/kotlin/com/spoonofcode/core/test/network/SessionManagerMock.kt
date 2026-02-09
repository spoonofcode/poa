package com.spoonofcode.core.test.network

import com.spoonofcode.core.data.network.SessionManager
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionManagerMock() = mock<SessionManager>(mode = MockMode.autoUnit) {
    every { clearSession() } returns Unit
}