package com.spoonofcode.core.network.di

import com.spoonofcode.core.data.logging.KermitLogger
import com.spoonofcode.core.data.logging.POALogger
import com.spoonofcode.core.network.HttpClientFactory
import com.spoonofcode.core.network.HttpLogoutHandler
import com.spoonofcode.core.network.NetworkConfig
import com.spoonofcode.core.network.NetworkConfigImpl
import com.spoonofcode.core.network.remote.RemoteRefreshTokenDataSource
import com.spoonofcode.core.session.domain.repository.LogoutHandler
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule: Module = module {
    singleOf(::RemoteRefreshTokenDataSource)
    single { NetworkConfigImpl() } bind NetworkConfig::class
    single<POALogger> { KermitLogger }
    single {
        HttpClientFactory(get(), get(), get()).create()
    }
    factory<LogoutHandler> { HttpLogoutHandler() }
}