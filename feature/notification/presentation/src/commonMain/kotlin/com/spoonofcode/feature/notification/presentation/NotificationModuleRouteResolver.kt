package com.spoonofcode.feature.notification.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelScreen
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen
import com.spoonofcode.feature.notification.presentation.type.NotificationTypeScreen

class NotificationModuleRouteResolver : ModuleRouteResolver<NotificationModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? NotificationModuleRoute)?.let {
            when (it) {
                is NotificationModuleRoute.NotificationDetails -> NotificationDetailsScreen(
                    notificationId = it.notificationId
                )

                is NotificationModuleRoute.NotificationEdit -> NotificationEditScreen(
                    notificationType = NotificationType.fromString(it.notificationTypeName)
                        ?: NotificationType.global,
                    selectedNames = it.selectedNames,
                    selectedTopics = it.selectedTopics,
                )

                NotificationModuleRoute.NotificationOverview -> NotificationOverviewScreen()
                is NotificationModuleRoute.NotificationSettingsScreen -> NotificationSettingsScreen(
                    backNavigationEnable = it.backNavigationEnabled
                )

                NotificationModuleRoute.NotificationType -> NotificationTypeScreen()
                NotificationModuleRoute.NotificationChannel -> NotificationChannelScreen()
            }
        }
}