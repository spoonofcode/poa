package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.presentation.NotificationModuleRouteResolver
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelViewModel
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewModel
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewModel
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notificationPresentationModule = module {
    factoryOf(::NotificationModuleRouteResolver) { bind<ModuleRouteResolver<NotificationModuleRoute>>() }

    viewModelOf(::NotificationOverviewViewModel)
    viewModelOf(::NotificationChannelViewModel)
    viewModelOf(::NotificationDetailsViewModel)
    viewModelOf(::NotificationSettingsViewModel)
}