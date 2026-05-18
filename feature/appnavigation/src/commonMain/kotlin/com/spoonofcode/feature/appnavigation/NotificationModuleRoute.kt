package com.spoonofcode.feature.appnavigation

sealed class NotificationModuleRoute : ModuleRoute() {
    data class NotificationDetails(
        val notificationId: String,
    ) : NotificationModuleRoute()

    data class NotificationEdit(
        val notificationTypeName: String,
        val selectedNames: Set<String> = emptySet(),
        val selectedTopics: Set<String> = emptySet(),
    ) : NotificationModuleRoute()

    object NotificationOverview : NotificationModuleRoute()
    data class NotificationSettings(
        val backNavigationEnabled: Boolean = true,
    ) : NotificationModuleRoute()

    data class NotificationType(
        val partnerId: String,
    ) : NotificationModuleRoute()
    object NotificationChannel : NotificationModuleRoute()
}