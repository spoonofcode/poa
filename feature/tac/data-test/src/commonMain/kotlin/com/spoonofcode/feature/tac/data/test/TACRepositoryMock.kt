package com.spoonofcode.feature.tac.data.test

import com.spoonofcode.feature.tac.domain.repository.TACRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun tacRepositoryMock() = mock<TACRepository> {
    everySuspend { getTACSettings() } returns TACMockData.SETTINGS_1
    everySuspend {
        setTACSettings(
            any(),
            any(),
        )
    } returns Unit
}