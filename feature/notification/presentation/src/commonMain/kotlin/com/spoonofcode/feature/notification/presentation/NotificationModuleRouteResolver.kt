package com.spoonofcode.feature.notification.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelScreen
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.notificationModuleRouteResolver() {
    entry<NotificationModuleRoute.NotificationDetails> {
        NotificationDetailsScreen(
            viewModel = koinViewModel(
                parameters = { parametersOf(it.notificationId) }
            ),
        ).Content()
    }
    entry<NotificationModuleRoute.NotificationOverview> {
        NotificationOverviewScreen(
            viewModel = koinViewModel(),
        ).Content()
    }

    entry<NotificationModuleRoute.NotificationSettings> {
        NotificationSettingsScreen(
            backNavigationEnable = it.backNavigationEnabled,
            viewModel = koinViewModel(),
        ).Content()
    }

    entry<NotificationModuleRoute.NotificationChannel> {
        NotificationChannelScreen(
            viewModel = koinViewModel(),
        ).Content()
    }
}