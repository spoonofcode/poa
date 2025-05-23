package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.model.Profile
import com.spoonofcode.poa.core.model.ProfileRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking

internal fun profileRepositoryMock() = mock<ProfileRepository>()
    .apply {
        runBlocking {
            everySuspend { create(any<ProfileRequest>()) } returns Profile(
                name = "dsa",
                numberOfEventsUserParticipatedIn = 1,
                numberOfEventsCreatedByUser = 1
            )
        }
    }
    .apply {
        runBlocking {
            everySuspend { read(any()) } returns Profile(
                name = "Profile name 1",
                numberOfEventsUserParticipatedIn = 1,
                numberOfEventsCreatedByUser = 1
            )
        }
    }