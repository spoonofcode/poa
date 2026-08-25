package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NotificationModuleRoute : ModuleRoute() {
    @Serializable
    data class NotificationDetails(
        val notificationId: String,
    ) : NotificationModuleRoute()

    @Serializable
    object NotificationOverview : NotificationModuleRoute()

    @Serializable
    data class NotificationSettings(
        val backNavigationEnabled: Boolean = true,
    ) : NotificationModuleRoute()

    @Serializable
    object NotificationChannel : NotificationModuleRoute()
}
