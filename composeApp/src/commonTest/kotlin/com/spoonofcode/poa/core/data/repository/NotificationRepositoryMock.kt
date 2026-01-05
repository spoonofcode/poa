package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.feature.notification.data.mock.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.poa.feature.notification.data.mock.NotificationMockData.NOTIFICATION_1
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun notificationRepositoryMock() = mock<NotificationDataSource> {
    everySuspend { create(any(), any(), any(), any(), any(), any(), any()) } returns Result.success(Unit)
    everySuspend { read(any()) } returns Result.success(NOTIFICATION_1)
    everySuspend { readAll() } returns Result.success(NOTIFICATIONS)
}