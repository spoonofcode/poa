package com.spoonofcode.core.firebase.data.di

import com.spoonofcode.core.firebase.data.remote.RemoteFirebaseMessagingDataSource
import com.spoonofcode.core.firebase.data.repository.FirebaseMessagingRepositoryImpl
import com.spoonofcode.core.firebase.domain.repository.FirebaseMessagingRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformFirebaseDataModule =
    module {
        singleOf(::RemoteFirebaseMessagingDataSource)
        singleOf(::FirebaseMessagingRepositoryImpl) { bind<FirebaseMessagingRepository>() }
    }
