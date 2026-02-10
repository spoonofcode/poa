package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import org.koin.dsl.module

val notificationPresentationTestModule = module {
    includes(
        notificationDomainModule,
        notificationPresentationModule,
    )
}