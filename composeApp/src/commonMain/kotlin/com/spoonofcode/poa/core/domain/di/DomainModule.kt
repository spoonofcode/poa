package com.spoonofcode.poa.core.domain.di

import com.spoonofcode.poa.core.domain.usecase.SubscribeUserTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SubscribeUserTopicsUseCase)
}