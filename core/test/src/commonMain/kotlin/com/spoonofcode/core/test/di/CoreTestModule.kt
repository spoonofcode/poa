package com.spoonofcode.core.test.di

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolverImpl
import com.spoonofcode.core.presentation.network.NetworkManager
import com.spoonofcode.core.test.network.FakeNetworkManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreTestModule = module {
    singleOf(::FakeNetworkManager).bind(NetworkManager::class)

    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)

}