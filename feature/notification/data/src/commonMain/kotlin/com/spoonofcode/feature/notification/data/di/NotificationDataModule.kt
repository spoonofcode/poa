package com.spoonofcode.feature.notification.data.di

import com.spoonofcode.feature.notification.data.repository.NotificationRepositoryImpl
import com.spoonofcode.feature.notification.data.local.LocalNotificationDataSource
import com.spoonofcode.feature.notification.data.remote.RemoteNotificationDataSource
import com.spoonofcode.feature.notification.domain.repository.NotificationRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val notificationDataModule = module {
    singleOf(::LocalNotificationDataSource)
    singleOf(::RemoteNotificationDataSource)

    singleOf(::NotificationRepositoryImpl).bind<NotificationRepository>()
}