package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.everySuspend

fun partnerNotificationRepositoryMock() = mock<PartnerNotificationRepository> {
    everySuspend { createNotification(any(), any(), any(), any(), any(), any(), any()) } returns Result.success(Unit)
    everySuspend { updateNotification(any(), any(), any(), any(), any(), any(), any(), any()) } returns Result.success(Unit)
    everySuspend { getNotifications() } returns Result.success(emptyList())
    everySuspend { getNotification(any()) } returns Result.success(mock())
    everySuspend { deleteNotification(any()) } returns Result.success(Unit)
}
