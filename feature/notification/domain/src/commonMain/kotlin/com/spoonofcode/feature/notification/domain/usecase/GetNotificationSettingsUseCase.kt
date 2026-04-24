package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.session.domain.SessionRepository
import com.spoonofcode.core.session.domain.model.NotificationSettings

class GetNotificationSettingsUseCase(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Result<NotificationSettings> = runCatching {
        sessionRepository.getNotificationSettings()
    }
}