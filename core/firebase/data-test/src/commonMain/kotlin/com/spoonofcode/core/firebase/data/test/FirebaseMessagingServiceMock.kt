package com.spoonofcode.core.firebase.data.test

import com.spoonofcode.core.firebase.data.FirebaseMessagingService
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun firebaseMessagingServiceMock() = mock<FirebaseMessagingService> {
    everySuspend { subscribeToTopic(any()) } returns Unit
    everySuspend { unsubscribeFromTopic(any()) } returns Unit
}