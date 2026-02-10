package com.spoonofcode.feature.profile.data.test

import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { readUserProfile() } returns Result.success(ProfileMockData.PROFILE_1)
}