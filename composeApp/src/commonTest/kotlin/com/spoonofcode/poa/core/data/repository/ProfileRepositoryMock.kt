package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.ProfileMockData.PROFILE_1
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { readUserProfile() } returns Result.success(PROFILE_1)
}