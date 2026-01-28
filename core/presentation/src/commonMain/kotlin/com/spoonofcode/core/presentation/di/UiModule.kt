package com.spoonofcode.core.presentation.di

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolverImpl
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