package com.spoonofcode.feature.home.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.home.presentation.HomeModuleRouteResolver
import com.spoonofcode.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    factoryOf(::HomeModuleRouteResolver) { bind<ModuleRouteResolver<HomeModuleRoute>>() }

    viewModelOf(::HomeViewModel)
}