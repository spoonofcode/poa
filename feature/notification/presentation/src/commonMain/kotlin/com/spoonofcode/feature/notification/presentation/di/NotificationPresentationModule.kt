package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.feature.notification.presentation.NotificationEventBus
import com.spoonofcode.feature.notification.presentation.channel.NotificationChannelViewModel
import com.spoonofcode.feature.notification.presentation.details.NotificationDetailsViewModel
import com.spoonofcode.feature.notification.presentation.overview.NotificationOverviewViewModel
import com.spoonofcode.feature.notification.presentation.settings.NotificationSettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notificationPresentationModule = module {
    singleOf(::NotificationEventBus)
    viewModelOf(::NotificationOverviewViewModel)
    viewModelOf(::NotificationChannelViewModel)
    viewModelOf(::NotificationDetailsViewModel)
    viewModelOf(::NotificationSettingsViewModel)
}