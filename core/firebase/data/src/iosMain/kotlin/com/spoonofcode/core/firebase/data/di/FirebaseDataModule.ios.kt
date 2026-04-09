package com.spoonofcode.core.firebase.data.di

import com.spoonofcode.core.firebase.data.FirebaseMessagingService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformFirebaseDataModule = module {
    factoryOf(::FirebaseMessagingService)
}