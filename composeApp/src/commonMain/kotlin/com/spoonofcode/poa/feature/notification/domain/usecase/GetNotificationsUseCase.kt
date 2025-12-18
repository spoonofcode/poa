package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
import com.spoonofcode.poa.feature.notification.domain.model.Notification

class GetNotificationsUseCase(
    private val notificationDataSource: NotificationDataSource,
) {
    suspend operator fun invoke(): Result<List<Notification>> = notificationDataSource.readAll()
        .map {
            it.sortedWith(
                compareBy<Notification> { it.alreadyRead } // false first
                    .thenByDescending { it.expirationDateTime } // newest first
            )
        }
}