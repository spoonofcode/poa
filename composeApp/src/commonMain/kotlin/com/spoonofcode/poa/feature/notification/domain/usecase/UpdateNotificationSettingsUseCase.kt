package com.spoonofcode.poa.feature.notification.domain.usecase

import com.spoonofcode.poa.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.utils.FirebasePushService

class UpdateNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
    private val firebasePushService: FirebasePushService,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings) {
        if (notificationSettings.notificationFromAllPartnersEnable) {
            firebasePushService.subscribeToTopic("all_partners")
        } else {
            firebasePushService.unsubscribeFromTopic("all_partners")
        }

        if (notificationSettings.notificationFromMyPartnersEnable) {
            firebasePushService.subscribeToTopic("my_partners")
        } else {
            firebasePushService.unsubscribeFromTopic("my_partners")
        }

        sessionManager.saveSettingsNotificationFromAllPartners(notificationSettings.notificationFromAllPartnersEnable)
        sessionManager.saveSettingsNotificationFromMyPartners(notificationSettings.notificationFromMyPartnersEnable)
    }
}