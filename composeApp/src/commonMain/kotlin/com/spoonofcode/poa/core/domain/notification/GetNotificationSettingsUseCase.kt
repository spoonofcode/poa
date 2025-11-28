package com.spoonofcode.poa.core.domain.notification

import com.spoonofcode.poa.core.model.NotificationSettings
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