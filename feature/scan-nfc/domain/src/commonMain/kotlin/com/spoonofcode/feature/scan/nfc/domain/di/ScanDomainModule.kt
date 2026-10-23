package com.spoonofcode.feature.scan.nfc.domain.di

import com.spoonofcode.feature.scan.nfc.domain.usecase.GetNFCTagUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val scanDomainModule =
    module {
        factoryOf(::GetNFCTagUseCase)
    }
