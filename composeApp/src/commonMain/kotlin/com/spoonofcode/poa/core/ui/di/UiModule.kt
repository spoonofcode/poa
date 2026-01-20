package com.spoonofcode.poa.core.ui.di

import com.spoonofcode.poa.core.ui.navigation.route.VoyagerRouteResolver
import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigator
import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigatorImpl
import com.spoonofcode.poa.core.ui.navigation.route.VoyagerRouteResolverImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformUiModule: Module

val uiModule = module {
    includes(platformUiModule)
    single { VoyagerRouteResolverImpl(getAll()) } bind VoyagerRouteResolver::class
    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)
}