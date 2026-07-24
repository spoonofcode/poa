package com.spoonofcode.feature.home.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.homeModuleRouteResolver() {
    entry<HomeModuleRoute.Home> {
        HomeScreen(
            viewModel = koinViewModel()
        ).Content()
    }
}