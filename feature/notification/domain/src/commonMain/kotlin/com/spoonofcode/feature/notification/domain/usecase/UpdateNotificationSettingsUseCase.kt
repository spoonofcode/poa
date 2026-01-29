package com.spoonofcode.feature.notification.domain.usecase

import com.spoonofcode.feature.notification.domain.model.NotificationSettings
import com.spoonofcode.core.data.network.SessionManager

class UpdateNotificationSettingsUseCase(
    private val sessionManager: SessionManager,
    // TODO #109 Fix after migration to feature modules
//    private val firebasePushService: FirebasePushService,
) {
    suspend operator fun invoke(notificationSettings: NotificationSettings) {
// TODO #109 Fix after migration to feature modules
        //        if (notificationSettings.notificationFromAllPartnersEnable) {
//            firebasePushService.subscribeToTopic("all_partners")
//        } else {
//            firebasePushService.unsubscribeFromTopic("all_partners")
//        }
//
//        if (notificationSettings.notificationFromMyPartnersEnable) {
//            firebasePushService.subscribeToTopic("my_partners")
//        } else {
//            firebasePushService.unsubscribeFromTopic("my_partners")
//        }

        sessionManager.saveSettingsNotificationFromAllPartners(notificationSettings.notificationFromAllPartnersEnable)
        sessionManager.saveSettingsNotificationFromMyPartners(notificationSettings.notificationFromMyPartnersEnable)
    }
}