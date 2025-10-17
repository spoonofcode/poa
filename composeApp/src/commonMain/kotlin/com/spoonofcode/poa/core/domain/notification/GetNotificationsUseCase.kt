package com.spoonofcode.poa.core.domain.notification

import com.spoonofcode.poa.core.data.repository.NotificationRepository
import com.spoonofcode.poa.core.model.Notification

class GetNotificationsUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(): List<Notification> = notificationRepository.readAll()
        .sortedWith(
            compareBy<Notification> { it.alreadyRead } // false first
                .thenByDescending { it.expirationDateTime } // newest first
        )
}