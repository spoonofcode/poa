package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.NotificationDataSource
import com.spoonofcode.poa.feature.notification.domain.model.Notification

class GetNotificationByIdUseCase(
    private val notificationDataSource: NotificationDataSource,
) {
    suspend operator fun invoke(
        notificationId: String
    ): Result<Notification> = notificationDataSource.read(notificationId = notificationId)
}