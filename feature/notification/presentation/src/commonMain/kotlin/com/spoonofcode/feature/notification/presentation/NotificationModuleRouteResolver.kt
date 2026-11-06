package com.spoonofcode.feature.notification.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelScreen
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsScreen
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewScreen
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.notificationModuleRouteResolver() {
    viewModelEntry<NotificationModuleRoute.NotificationDetails> {
        NotificationDetailsScreen(
            viewModel =
                koinViewModel(
                    parameters = { parametersOf(it.notificationId) },
                ),
        )
    }
    viewModelEntry<NotificationModuleRoute.NotificationOverview> {
        NotificationOverviewScreen(
            viewModel = koinViewModel(),
        )
    }

    viewModelEntry<NotificationModuleRoute.NotificationSettings> {
        NotificationSettingsScreen(
            backNavigationEnable = it.backNavigationEnabled,
            viewModel = koinViewModel(),
        )
    }

    viewModelEntry<NotificationModuleRoute.NotificationChannel> {
        NotificationChannelScreen(
            viewModel = koinViewModel(),
        )
    }
}
