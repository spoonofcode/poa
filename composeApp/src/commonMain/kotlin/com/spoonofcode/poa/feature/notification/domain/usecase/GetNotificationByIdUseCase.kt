package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.model.Notification
import com.spoonofcode.poa.feature.notification.domain.repository.NotificationRepository

class GetNotificationByIdUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(
        notificationId: String
    ): Result<Notification> = notificationRepository.read(notificationId = notificationId)
}