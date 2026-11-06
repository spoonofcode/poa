package com.spoonofcode.feature.notification.domain.di

import com.spoonofcode.core.firebase.data.test.di.firebaseDataTestModule
import com.spoonofcode.core.logging.di.loggingModule
import com.spoonofcode.feature.notification.data.test.di.notificationDataTestModule
import org.koin.dsl.module

val notificationDomainTestModule =
    module {
        includes(
            loggingModule,
            firebaseDataTestModule,
            notificationDataTestModule,
            notificationDomainModule,
        )
    }
