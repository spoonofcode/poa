package com.spoonofcode.feature.partner.data.test.di

import com.spoonofcode.feature.partner.data.test.partnerNotificationRepositoryMock
import com.spoonofcode.feature.partner.data.test.partnerRepositoryMock
import org.koin.dsl.module

val partnerDataTestModule = module {
    includes(
        module {
            single { partnerRepositoryMock() }
            single { partnerNotificationRepositoryMock() }
        },
    )
}
