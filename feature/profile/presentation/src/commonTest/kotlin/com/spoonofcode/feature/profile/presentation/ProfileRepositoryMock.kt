package com.spoonofcode.feature.profile.presentation

import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import com.spoonofcode.feature.profile.presentation.ProfileMockData.PROFILE_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { readUserProfile() } returns Result.success(PROFILE_1)
}