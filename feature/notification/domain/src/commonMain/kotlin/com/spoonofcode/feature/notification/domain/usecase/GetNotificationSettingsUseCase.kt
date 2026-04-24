package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.core.data.network.NotificationSettings
import com.spoonofcode.core.data.network.SessionManager

class GetNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Result<NotificationSettings> = runCatching {
        sessionManager.getNotificationSettings()
    }
}