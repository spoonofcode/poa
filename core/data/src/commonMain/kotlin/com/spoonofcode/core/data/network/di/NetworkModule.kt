package com.spoonofcode.core.data.network.di

import com.spoonofcode.core.data.network.RemoteRefreshTokenDataSource
import com.spoonofcode.core.data.logging.KermitLogger
import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.data.network.HttpClientFactory
import com.spoonofcode.core.data.network.SessionRepository
import com.spoonofcode.core.data.network.SessionRepositoryKSafeImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule: Module = module {
    singleOf(::SessionRepositoryKSafeImpl).bind(SessionRepository::class)
    singleOf(::RemoteRefreshTokenDataSource)

    single<POALogger> { KermitLogger }
    single {
        HttpClientFactory(get(), get(), get()).create()
    }
}