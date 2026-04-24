package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.data.network.NotificationSettings
import com.spoonofcode.core.data.network.SessionRepository

class GetNotificationSettingsUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Result<NotificationSettings> = runCatching {
        sessionRepository.getNotificationSettings()
    }
}