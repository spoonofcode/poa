package com.spoonofcode.feature.partner.data.test

import com.spoonofcode.feature.partner.domain.model.PartnerNotification
import com.spoonofcode.feature.partner.domain.repository.PartnerNotificationRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.datetime.LocalDateTime

fun partnerNotificationRepositoryMock() =
    mock<PartnerNotificationRepository> {
        everySuspend { createNotification(any(), any(), any(), any(), any(), any(), any()) } returns Result.success(Unit)
        everySuspend {
            updateNotification(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
            )
        } returns Result.success(Unit)
        everySuspend { getNotifications() } returns Result.success(emptyList())
        everySuspend { getNotification(any()) } returns
            Result.success(
                PartnerNotification(
                    id = "1",
                    title = "Title",
                    text = "Text",
                    deliveryDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    createdDataTime = LocalDateTime(2023, 1, 1, 1, 1),
                    modificationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                    state = "pending",
                    topics = listOf("global"),
                    ownerId = "owner",
                ),
            )
        everySuspend { deleteNotification(any()) } returns Result.success(Unit)
    }
