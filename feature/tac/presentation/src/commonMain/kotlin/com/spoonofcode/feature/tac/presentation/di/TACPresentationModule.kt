package com.spoonofcode.feature.tac.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.tac.presentation.TACModuleRouteResolver
import com.spoonofcode.feature.tac.presentation.TACViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tacPresentationModule = module {
    factoryOf(::TACModuleRouteResolver) { bind<ModuleRouteResolver<TACModuleRoute>>() }

    viewModelOf(::TACViewModel)
}