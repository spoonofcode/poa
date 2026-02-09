package com.spoonofcode.feature.auth.presentation.di

import org.koin.dsl.module

val authTestModule = module {
    includes(
        authPresentationModule
    )
}