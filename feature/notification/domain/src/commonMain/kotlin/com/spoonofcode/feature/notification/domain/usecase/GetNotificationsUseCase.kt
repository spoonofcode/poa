package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.Notification
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository

class GetNotificationsUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(): Result<List<Notification>> =
        notificationRepository
            .readAll()
            .map { notifications ->
                notifications.sortedWith(
                    compareBy<Notification> { it.isRead } // Unread first (false < true)
                        .thenByDescending { it.createdDataTime }, // Newest first
                )
            }
}
