package com.spoonofcode.feature.scan.nfc.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ScanModuleRoute
import com.spoonofcode.feature.scan.nfc.presentation.ScanModuleRouteResolver
import com.spoonofcode.feature.scan.nfc.presentation.ScanViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scanPresentationModule = module {
    factoryOf(::ScanModuleRouteResolver) { bind<ModuleRouteResolver<ScanModuleRoute>>() }

    viewModelOf(::ScanViewModel)
}