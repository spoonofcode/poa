package com.spoonofcode.core.test.di

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.data.network.SessionManagerImpl
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolverImpl
import com.spoonofcode.core.presentation.network.NetworkManager
import com.spoonofcode.core.test.FakeVoyagerRouteResolver
import com.spoonofcode.core.test.network.FakeNetworkManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

// TODO #131 Fix test 
val coreTestModule = module {
    single<Settings> { get<Settings.Factory>().create("poa-settings") }
    single<Settings> { MapSettings() }

    singleOf(::SessionManagerImpl).bind(SessionManager::class)

    singleOf(::FakeNetworkManager).bind(NetworkManager::class)

    singleOf(::FakeVoyagerRouteResolver).bind(VoyagerRouteResolver::class)

    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)
//    single { VoyagerRouteResolverImpl(getAll()) } bind VoyagerRouteResolver::class
}