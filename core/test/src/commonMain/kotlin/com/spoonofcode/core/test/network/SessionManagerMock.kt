package com.spoonofcode.core.test.network

import com.spoonofcode.core.data.network.SessionRepository
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionManagerMock() = mock<SessionRepository>(mode = MockMode.autoUnit) {
    every { clearSession() } returns Unit
}