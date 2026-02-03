package com.spoonofcode.core.presentation.di

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolverImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val uiModule = module {
    single { VoyagerRouteResolverImpl(getAll()) } bind VoyagerRouteResolver::class
    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)
}