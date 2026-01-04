package com.spoonofcode.poa.feature.scan.di

import com.spoonofcode.poa.feature.scan.presentation.ScanViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scanModule = module {
    viewModelOf(::ScanViewModel)
}