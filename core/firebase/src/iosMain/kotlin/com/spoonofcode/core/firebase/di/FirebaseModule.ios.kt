package com.spoonofcode.core.firebase.di

import com.spoonofcode.core.firebase.FirebaseMessagingService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformFirebaseModule = module {
    factoryOf(::FirebaseMessagingService)
}