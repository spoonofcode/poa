package com.spoonofcode.feature.appnavigation

sealed class NotificationModule : Screen() {
    data class NotificationDetailsScreen(
        val notificationId: String,
    ) : NotificationModule()

    data class NotificationEditScreen(
        val onlyToSelectedCustomers: Boolean = false,
        val selectedNames: Set<String> = emptySet(),
        val selectedTopics: Set<String> = emptySet(),
    ) : NotificationModule()

    object NotificationOverviewScreen : NotificationModule()
    object NotificationSettingsScreen : NotificationModule()
    object NotificationTypeScreen : NotificationModule()
}