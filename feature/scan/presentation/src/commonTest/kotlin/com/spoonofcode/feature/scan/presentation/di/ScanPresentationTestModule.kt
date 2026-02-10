package com.spoonofcode.feature.scan.presentation.di

import org.koin.dsl.module

val scanPresentationTestModule = module {
    includes(
        scanPresentationModule,
    )
}