package com.spoonofcode.feature.profile.data.di

import com.spoonofcode.feature.profile.data.repository.ProfileRepositoryImpl
import com.spoonofcode.feature.profile.data.remote.RemoteProfileDataSource
import com.spoonofcode.feature.profile.domain.repository.ProfileRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileDataModule = module {
    singleOf(::RemoteProfileDataSource)
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
}