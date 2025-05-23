package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.poa.core.model.ProfileRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { create(any<ProfileRequest>()) } returns PROFILE_1
    everySuspend { read(any()) } returns PROFILE_1
}