package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.feature.notification.domain.usecase.SendNotificationUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import com.spoonofcode.feature.notification.presentation.NotificationModuleRouteResolver
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewModel
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditViewModel
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewModel
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel
import com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notificationPresentationModule = module {
    factoryOf(::NotificationModuleRouteResolver) { bind<ModuleRouteResolver<NotificationModule>>() }

    viewModelOf(::NotificationOverviewViewModel)
    viewModelOf(::NotificationDetailsViewModel)
    viewModelOf(::NotificationEditViewModel)
    viewModelOf(::NotificationTypeViewModel)
    viewModelOf(::NotificationSettingsViewModel)
}