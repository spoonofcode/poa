package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.repository.NotificationRepository

class MarkNotificationAsReadUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(notificationId: String) {
        notificationRepository.markAsRead(notificationId)
    }
}
