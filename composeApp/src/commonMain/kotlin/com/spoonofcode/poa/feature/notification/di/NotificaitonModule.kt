package com.spoonofcode.poa.feature.notification.di

import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.poa.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.poa.feature.notification.domain.usecase.SendNotificationUseCase
import com.spoonofcode.poa.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsViewModel
import com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditViewModel
import com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewViewModel
import com.spoonofcode.poa.feature.notification.presentation.settings.NotificationSettingsViewModel
import com.spoonofcode.poa.feature.notification.presentation.type.NotificationTypeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notificationModule = module {
    factoryOf(::SendNotificationUseCase)
    factoryOf(::GetNotificationsUseCase)
    factoryOf(::GetNotificationByIdUseCase)
    factoryOf(::GetNotificationSettingsUseCase)
    factoryOf(::UpdateNotificationSettingsUseCase)

    viewModelOf(::NotificationOverviewViewModel)
    viewModelOf(::NotificationDetailsViewModel)
    viewModelOf(::NotificationEditViewModel)
    viewModelOf(::NotificationTypeViewModel)
    viewModelOf(::NotificationSettingsViewModel)
}