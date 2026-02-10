package com.spoonofcode.core.firebase.di

import com.spoonofcode.core.firebase.usecase.SubscribeUserTopicsUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


expect val platformFirebaseModule: Module

val firebaseModule = module {
    includes(platformFirebaseModule)
    factoryOf(::SubscribeUserTopicsUseCase)
}