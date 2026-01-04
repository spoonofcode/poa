package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.poa.core.network.SessionManager

class GetNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke(): NotificationSettings =
        NotificationSettings(
            notificationFromAllPartnersEnable = sessionManager.getSettingsNotificationFromAllPartners(),
            notificationFromMyPartnersEnable = sessionManager.getSettingsNotificationFromMyPartners(),
        )
}