package com.spoonofcode.feature.notification.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelScreen
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen
import com.spoonofcode.feature.notification.presentation.type.NotificationTypeScreen

class NotificationModuleRouteResolver : ModuleRouteResolver<NotificationModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? NotificationModule)?.let {
            when (it) {
                is NotificationModule.NotificationDetailsScreen -> NotificationDetailsScreen(
                    notificationId = it.notificationId
                )

                is NotificationModule.NotificationEditScreen -> NotificationEditScreen(
                    notificationType = NotificationType.fromString(it.notificationTypeName)
                        ?: NotificationType.global,
                    selectedNames = it.selectedNames,
                    selectedTopics = it.selectedTopics,
                )

                NotificationModule.NotificationOverviewScreen -> NotificationOverviewScreen()
                is NotificationModule.NotificationSettingsScreen -> NotificationSettingsScreen(
                    backNavigationEnable = it.backNavigationEnabled
                )
                NotificationModule.NotificationTypeScreen -> NotificationTypeScreen()
                NotificationModule.NotificationChannelScreen -> NotificationChannelScreen()
            }
        }
}