package com.spoonofcode.core.test.network

import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionRepositoryMock() = mock<SessionRepository>(mode = MockMode.autoUnit) {
    every { clearSession() } returns Unit
}