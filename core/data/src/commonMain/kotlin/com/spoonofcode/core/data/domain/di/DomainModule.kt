package com.spoonofcode.core.data.domain.di

import com.spoonofcode.core.data.domain.usecase.SubscribeUserTopicsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SubscribeUserTopicsUseCase)
}