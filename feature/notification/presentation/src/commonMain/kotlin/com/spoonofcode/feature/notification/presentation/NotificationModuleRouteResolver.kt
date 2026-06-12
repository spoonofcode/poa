package com.spoonofcode.feature.notification.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelScreen
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen

class NotificationModuleRouteResolver : ModuleRouteResolver<NotificationModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? NotificationModuleRoute)?.let {
            when (it) {
                is NotificationModuleRoute.NotificationDetails -> NotificationDetailsScreen(
                    notificationId = it.notificationId
                )

                NotificationModuleRoute.NotificationOverview -> NotificationOverviewScreen()
                is NotificationModuleRoute.NotificationSettings -> NotificationSettingsScreen(
                    backNavigationEnable = it.backNavigationEnabled
                )

                NotificationModuleRoute.NotificationChannel -> NotificationChannelScreen()
            }
        }
}
