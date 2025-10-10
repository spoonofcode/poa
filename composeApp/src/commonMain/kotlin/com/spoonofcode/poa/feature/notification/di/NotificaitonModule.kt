package com.spoonofcode.poa.feature.notification.di

import com.spoonofcode.poa.feature.notification.details.NotificationDetailsViewModel
import com.spoonofcode.poa.feature.notification.edit.NotificationEditViewModel
import com.spoonofcode.poa.feature.notification.overview.NotificationOverviewViewModel
import com.spoonofcode.poa.feature.notification.type.NotificationTypeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notificationModule = module {
    viewModelOf(::NotificationOverviewViewModel)
    viewModelOf(::NotificationDetailsViewModel)
    viewModelOf(::NotificationEditViewModel)
    viewModelOf(::NotificationTypeViewModel)
}