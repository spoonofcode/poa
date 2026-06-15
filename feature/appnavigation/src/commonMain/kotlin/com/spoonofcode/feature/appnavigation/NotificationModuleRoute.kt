package com.spoonofcode.feature.appnavigation

sealed class NotificationModuleRoute : ModuleRoute() {
    data class NotificationDetails(
        val notificationId: String,
    ) : NotificationModuleRoute()

    object NotificationOverview : NotificationModuleRoute()
    data class NotificationSettings(
        val backNavigationEnabled: Boolean = true,
    ) : NotificationModuleRoute()

    object NotificationChannel : NotificationModuleRoute()
}
