package com.spoonofcode.poa.feature.login.di

import com.spoonofcode.core.presentation.utils.GoogleAuthProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformLoginModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}