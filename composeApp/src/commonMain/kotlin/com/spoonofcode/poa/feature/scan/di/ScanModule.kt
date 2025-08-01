package com.spoonofcode.poa.feature.scan.di

import com.spoonofcode.poa.feature.home.HomeViewModel
import com.spoonofcode.poa.feature.scan.ScanViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scanModule = module {
    viewModelOf(::ScanViewModel)
}