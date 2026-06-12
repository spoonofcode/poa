package com.spoonofcode.feature.appnavigation

sealed class PartnerModuleRoute : ModuleRoute() {
    data class PartnerDetails(
        val partnerId: String,
    ) : PartnerModuleRoute()

    data object PartnerEdit : PartnerModuleRoute()
    data object CustomerOverview : PartnerModuleRoute()
    data object PartnerPanel : PartnerModuleRoute()
    data object PartnerNotifications : PartnerModuleRoute()
    data class PartnerNotificationDetails(
        val notificationId: String,
    ) : PartnerModuleRoute()

    data class PartnerNotificationEdit(
        val notificationId: String? = null,
        val selectedNames: Set<String> = emptySet(),
        val selectedTopics: Set<String> = emptySet(),
    ) : PartnerModuleRoute()

    data object PartnerNotificationType : PartnerModuleRoute()
}
