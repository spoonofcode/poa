package com.spoonofcode.core.presentation.di

import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.ViewModelNavigatorImpl
import com.spoonofcode.core.presentation.network.NetworkManager
import com.spoonofcode.core.presentation.network.NetworkManagerImpl
import dev.jordond.connectivity.Connectivity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    single {
        Connectivity {
            autoStart = true
        }
    }
    singleOf(::NetworkManagerImpl).bind(NetworkManager::class)
    singleOf(::ViewModelNavigatorImpl).bind(ViewModelNavigator::class)
}