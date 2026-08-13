package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class PartnerModuleRoute : ModuleRoute() {
    @Serializable
    data class PartnerDetails(
        val partnerId: String,
    ) : PartnerModuleRoute()

    @Serializable
    data object PartnerOverview : PartnerModuleRoute()

    @Serializable
    data object PartnerEdit : PartnerModuleRoute()

    @Serializable
    data object CustomerOverview : PartnerModuleRoute()

    @Serializable
    data object PartnerPanel : PartnerModuleRoute()

    @Serializable
    data object PartnerNotifications : PartnerModuleRoute()

    @Serializable
    data class PartnerNotificationDetails(
        val notificationId: String,
    ) : PartnerModuleRoute()

    @Serializable
    data class PartnerNotificationEdit(
        val notificationId: String? = null,
        val selectedNames: Set<String> = emptySet(),
        val selectedTopics: Set<String> = emptySet(),
    ) : PartnerModuleRoute()

    @Serializable
    data object PartnerNotificationType : PartnerModuleRoute()
}
