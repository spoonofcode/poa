package com.spoonofcode.feature.notification.data.test.di

import com.spoonofcode.feature.notification.data.test.notificationRepositoryMock
import org.koin.dsl.module

val notificationDataTestModule = module {
    includes(
        module {
            single { notificationRepositoryMock() }
        },
    )
}