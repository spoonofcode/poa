package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.core.data.network.SessionManager

class GetNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke(): NotificationSettings =
        NotificationSettings(
            isNotificationsPersonalizedEnabled = sessionManager.getNotificationsPersonalizedSettings(),
            isNotificationsMyProductSeriesEnabled = sessionManager.getNotificationsMyProductSeriesSettings(),
        )
}