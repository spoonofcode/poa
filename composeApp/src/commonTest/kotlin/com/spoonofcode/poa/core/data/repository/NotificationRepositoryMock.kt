package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATION_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun notificationRepositoryMock() = mock<NotificationRepository> {
    everySuspend { create(any()) } returns NOTIFICATION_1
    everySuspend { read(any()) } returns NOTIFICATION_1
    everySuspend { readAll() } returns NOTIFICATIONS
}