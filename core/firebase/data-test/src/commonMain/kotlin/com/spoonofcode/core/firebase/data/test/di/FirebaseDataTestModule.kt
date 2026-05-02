package com.spoonofcode.core.firebase.data.test.di

import com.spoonofcode.core.firebase.data.test.firebaseMessagingRepositoryMock
import org.koin.dsl.module

val firebaseDataTestModule = module {
    includes(
        module {
            single { firebaseMessagingRepositoryMock() }
        },
    )
}