package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import org.koin.dsl.module

val notificationPresentationTestModule = module {
    includes(
        notificationDataTestModule,
        notificationDomainModule,
        notificationPresentationModule,
    )
}