package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository

class GetNotificationSettingsUseCase(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(): Result<NotificationSettings> = runCatching {
        notificationRepository.getNotificationSettings()
    }
}