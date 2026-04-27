package com.spoonofcode.feature.scan.data.di

import com.spoonofcode.feature.scan.data.NFCTagRepositoryImpl
import com.spoonofcode.feature.scan.data.remote.RemoteNFCTagDataSource
import com.spoonofcode.feature.scan.domain.repository.NFCTagRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val scanDataModule = module {
    singleOf(::RemoteNFCTagDataSource)

    singleOf(::NFCTagRepositoryImpl).bind<NFCTagRepository>()
}