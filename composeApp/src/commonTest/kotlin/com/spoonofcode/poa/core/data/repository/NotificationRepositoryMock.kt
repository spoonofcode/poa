package com.spoonofcode.poa.core.data.repository

import com.spoonofcode.poa.core.data.mockdata.NotificationMockData.NOTIFICATIONS
import com.spoonofcode.feature.notification.data.mock.NotificationMockData.NOTIFICATION_1
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun notificationRepositoryMock() = mock<NotificationRepository> {
    everySuspend { send(any(), any(), any(), any(), any(), any(), any()) } returns Result.success(
        Unit
    )
    everySuspend { read(any()) } returns Result.success(NOTIFICATION_1)
    everySuspend { readAll() } returns Result.success(NOTIFICATIONS)
}