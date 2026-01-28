package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.core.data.network.SessionManager

class GetNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
) {
    operator fun invoke(): NotificationSettings =
        NotificationSettings(
            notificationFromAllPartnersEnable = sessionManager.getSettingsNotificationFromAllPartners(),
            notificationFromMyPartnersEnable = sessionManager.getSettingsNotificationFromMyPartners(),
        )
}