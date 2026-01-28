package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

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