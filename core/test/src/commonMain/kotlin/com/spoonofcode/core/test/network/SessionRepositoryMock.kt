package com.spoonofcode.core.test.network

import com.spoonofcode.core.session.domain.repository.SessionRepository
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

fun sessionRepositoryMock() = mock<SessionRepository>(mode = MockMode.autoUnit) {
    everySuspend { getSessionUserEmail() } returns "testSessionUser@exampl.com"
    everySuspend { clearSession() } returns Unit
}
