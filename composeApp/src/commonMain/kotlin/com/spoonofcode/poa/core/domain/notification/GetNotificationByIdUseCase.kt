package com.spoonofcode.poa.core.domain.notification

import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.model.Notification

class GetNotificationByIdUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(
        notificationId: Int
    ): Notification = notificationRepository.read(id = notificationId)
}