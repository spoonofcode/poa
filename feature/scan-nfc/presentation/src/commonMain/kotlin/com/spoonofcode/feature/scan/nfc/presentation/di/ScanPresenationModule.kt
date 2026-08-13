package com.spoonofcode.feature.scan.nfc.presentation.di

import com.spoonofcode.feature.scan.nfc.presentation.ScanViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scanPresentationModule = module {
    viewModelOf(::ScanViewModel)
}