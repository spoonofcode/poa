package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.firebase.data.FirebaseMessagingService

class UpdateNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
    private val firebaseMessagingService: FirebaseMessagingService,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings) {
                if (notificationSettings.notificationFromAllPartnersEnable) {
            firebaseMessagingService.subscribeToTopic("all_partners")
        } else {
            firebaseMessagingService.unsubscribeFromTopic("all_partners")
        }

        if (notificationSettings.notificationFromMyPartnersEnable) {
            firebaseMessagingService.subscribeToTopic("my_partners")
        } else {
            firebaseMessagingService.unsubscribeFromTopic("my_partners")
        }

        sessionManager.saveSettingsNotificationFromAllPartners(notificationSettings.notificationFromAllPartnersEnable)
        sessionManager.saveSettingsNotificationFromMyPartners(notificationSettings.notificationFromMyPartnersEnable)
    }
}