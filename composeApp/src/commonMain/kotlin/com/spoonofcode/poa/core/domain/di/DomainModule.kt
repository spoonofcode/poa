package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.data.remote.RemoteRefreshDataSource
import com.spoonofcode.poa.core.domain.RefreshDataSource
import com.spoonofcode.poa.core.domain.usecase.RefreshAccessTokenUseCase
import com.spoonofcode.poa.core.domain.usecase.SubscribeUserTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    singleOf(::RemoteRefreshDataSource).bind<RefreshDataSource>()

    factoryOf(::RefreshAccessTokenUseCase)
    factoryOf(::SubscribeUserTopicsUseCase)
}