package com.spoonofcode.feature.scan.nfc.presentation.di

import org.koin.dsl.module

val scanPresentationTestModule = module {
    includes(
        scanPresentationModule,
    )
}