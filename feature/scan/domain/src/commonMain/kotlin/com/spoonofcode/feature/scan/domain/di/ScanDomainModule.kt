package com.spoonofcode.feature.scan.domain.di

import com.spoonofcode.feature.scan.domain.usecase.GetTagPublicIdUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val scanDomainModule = module {
    factoryOf(::GetTagPublicIdUseCase)
}