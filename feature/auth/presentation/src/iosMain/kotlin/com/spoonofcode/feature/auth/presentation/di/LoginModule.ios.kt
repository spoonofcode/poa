package com.spoonofcode.feature.auth.presentation.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformLoginModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}