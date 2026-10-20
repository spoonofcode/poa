package com.spoonofcode.feature.notification.presentation.di

import com.spoonofcode.core.firebase.data.test.di.firebaseDataTestModule
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import com.spoonofcode.feature.notification.domain.di.notificationDomainModule
import org.koin.dsl.module

val notificationPresentationTestModule = module {
    includes(
        loggingModule,
        notificationDataTestModule,
        notificationDomainModule,
        notificationPresentationModule,
        firebaseDataTestModule,
    )
}
