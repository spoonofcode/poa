package com.spoonofcode.poa.core.domain.notification

import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.model.Notification
import com.spoonofcode.poa.core.model.NotificationRequest

class SendNotificationUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(notification: NotificationRequest): Notification =
        notificationRepository.create(notification)
}