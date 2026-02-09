package com.spoonofcode.core.presentation.di

import com.spoonofcode.core.data.network.NetworkConfig
import com.spoonofcode.core.data.network.NetworkConfigImpl
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolverImpl
import com.spoonofcode.core.presentation.network.NetworkManager
import com.spoonofcode.core.presentation.network.NetworkManagerImpl
import dev.jordond.connectivity.Connectivity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val uiModule = module {
    single {
        Connectivity {
            autoStart = true
        }
    }
    singleOf(::NetworkManagerImpl).bind(NetworkManager::class)
    single { NetworkConfigImpl() } bind NetworkConfig::class
    single { VoyagerRouteResolverImpl(getAll()) } bind VoyagerRouteResolver::class
    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)
}