package com.spoonofcode.core.firebase.data.test

import com.spoonofcode.core.firebase.data.repository.FirebaseMessagingRepositoryImpl
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun firebaseMessagingRepositoryMock() = mock<FirebaseMessagingRepositoryImpl> {
    everySuspend { subscribeToTopic(any()) } returns Unit
    everySuspend { unsubscribeFromTopic(any()) } returns Unit
}