package com.spoonofcode.feature.appnavigation

sealed class NotificationModule : Screen() {
    data class NotificationDetailsScreen(
        val notificationId: String,
    ) : NotificationModule()

    data class NotificationEditScreen(
        val onlyToSelectedUsers: Boolean = false,
        val topics: List<String> = emptyList(),
        val itemIds: List<String> = emptyList(),
    ) : NotificationModule()

    object NotificationOverviewScreen : NotificationModule()
    object NotificationSettingsScreen : NotificationModule()
    object NotificationTypeScreen : NotificationModule()
}