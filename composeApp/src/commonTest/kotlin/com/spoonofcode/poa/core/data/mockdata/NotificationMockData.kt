package com.spoonofcode.poa.core.data.mockdata

import com.spoonofcode.feature.notification.domain.model.Notification
import kotlinx.datetime.LocalDateTime

object NotificationMockData {
    val NOTIFICATION_1 = Notification(
        id = "1",
        title = "Notification title 1",
        text = "Notification text 1",
        link = "Notification link 1",
        expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
        alreadyRead = false,
    )

    val NOTIFICATION_2 = Notification(
        id = "2",
        title = "Notification title 2",
        text = "Notification text 2",
        link = "Notification link 2",
        expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
        alreadyRead = false,
    )

    val NOTIFICATION_3 = Notification(
        id = "3",
        title = "Notification title 3",
        text = "Notification text 3",
        link = "Notification link 3",
        expirationDateTime = LocalDateTime(2023, 1, 1, 1, 1),
        alreadyRead = false,
    )

    val NOTIFICATIONS = listOf(NOTIFICATION_1, NOTIFICATION_2, NOTIFICATION_3)
}