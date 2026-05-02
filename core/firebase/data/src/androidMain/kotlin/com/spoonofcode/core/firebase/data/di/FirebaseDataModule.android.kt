package com.spoonofcode.core.firebase.data.di

import com.spoonofcode.core.firebase.data.repository.FirebaseMessagingRepositoryImpl
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual val platformFirebaseDataModule = module {
    factoryOf(::FirebaseMessagingRepositoryImpl) { bind<FirebaseMessagingRepository>() }
}