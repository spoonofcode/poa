package com.spoonofcode.feature.notification.domain.di

import com.spoonofcode.feature.notification.domain.usecase.GetNotificationByIdUseCase
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationSettingsUseCase
import com.spoonofcode.feature.notification.domain.usecase.GetNotificationsUseCase
import com.spoonofcode.feature.notification.domain.usecase.MarkNotificationAsReadUseCase
import com.spoonofcode.feature.notification.domain.usecase.NotificationTopicSyncHandler
import com.spoonofcode.feature.notification.domain.usecase.SendNotificationUseCase
import com.spoonofcode.feature.notification.domain.usecase.UpdateNotificationSettingsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val notificationDomainModule = module {
    factoryOf(::SendNotificationUseCase)
    factoryOf(::GetNotificationsUseCase)
    factoryOf(::GetNotificationByIdUseCase)
    factoryOf(::GetNotificationSettingsUseCase)
    factory {
        UpdateNotificationSettingsUseCase(
            sessionRepository = get(),
            notificationRepository = get(),
            firebaseMessagingRepository = get(),
            topicSyncHandlers = getAll<NotificationTopicSyncHandler>()
        )
    }
    factoryOf(::MarkNotificationAsReadUseCase)
}
