package com.spoonofcode.feature.auth.data.di

import com.spoonofcode.feature.auth.data.LoginRepositoryImpl
import com.spoonofcode.feature.auth.data.remote.RemoteLoginCodeDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginGoogleDataSource
import com.spoonofcode.feature.auth.data.remote.RemoteLoginRequestCodeDataSource
import com.spoonofcode.feature.auth.domain.repository.LoginRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule: Module = module {
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    singleOf(::RemoteLoginGoogleDataSource)

    singleOf(::RemoteLoginCodeDataSource)
    singleOf(::RemoteLoginRequestCodeDataSource)
}