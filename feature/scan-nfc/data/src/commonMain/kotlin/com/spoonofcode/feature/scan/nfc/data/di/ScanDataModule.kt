package com.spoonofcode.feature.scan.nfc.data.di

import com.spoonofcode.feature.scan.nfc.data.remote.RemoteNFCTagDataSource
import com.spoonofcode.feature.scan.nfc.data.repository.NFCTagRepositoryImpl
import com.spoonofcode.feature.scan.nfc.domain.repository.NFCTagRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val scanDataModule =
    module {
        singleOf(::RemoteNFCTagDataSource)

        singleOf(::NFCTagRepositoryImpl).bind<NFCTagRepository>()
    }
