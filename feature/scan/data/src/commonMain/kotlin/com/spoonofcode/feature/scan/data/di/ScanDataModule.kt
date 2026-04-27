package com.spoonofcode.feature.scan.data.di

import com.spoonofcode.feature.scan.data.TagRepositoryImpl
import com.spoonofcode.feature.scan.data.remote.RemoteTagDataSource
import com.spoonofcode.feature.scan.domain.repository.TagRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val scanDataModule = module {
    singleOf(::RemoteTagDataSource)

    singleOf(::TagRepositoryImpl).bind<TagRepository>()
}