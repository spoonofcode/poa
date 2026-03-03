package com.spoonofcode.core.firebase.data.test.di

import com.spoonofcode.core.firebase.data.test.firebaseMessagingServiceMock
import org.koin.dsl.module

val firebaseDataTestModule = module {
    includes(
        module {
            single { firebaseMessagingServiceMock() }
        },
    )
}