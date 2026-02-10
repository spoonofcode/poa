package com.spoonofcode.feature.scan.presentation.di

import com.spoonofcode.core.test.di.coreTestModule
import org.koin.dsl.module

val scanPresentationTestModule = module {
    includes(
        coreTestModule,
        scanPresentationModule,
    )
}