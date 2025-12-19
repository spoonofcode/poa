package com.spoonofcode.poa.core.data.di

import com.spoonofcode.poa.core.data.repository.RefreshRepository
import com.spoonofcode.poa.core.data.repository.RefreshRepositoryImpl
import com.spoonofcode.poa.core.data.repository.RemoteUserDataSource
import com.spoonofcode.poa.core.data.repository.UserDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RefreshRepositoryImpl) { bind<RefreshRepository>() }
    singleOf(::RemoteUserDataSource) { bind<UserDataSource>() }
}