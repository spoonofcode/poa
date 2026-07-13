package com.spoonofcode.core.firebase.data.test

import com.spoonofcode.core.firebase.data.repository.FirebaseMessagingRepositoryImpl
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun firebaseMessagingRepositoryMock() = mock<FirebaseMessagingRepositoryImpl> {
    everySuspend { getMessageToken() } returns Result.success("test-token")
    everySuspend { subscribeToTopic(any()) } returns Result.success(Unit)
    everySuspend { unsubscribeFromTopic(any()) } returns Result.success(Unit)
}