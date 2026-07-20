package com.spoonofcode.feature.auth.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.authModuleRouteResolver() {
    entry<AuthModuleRoute.Login> {
        LoginScreen(
            viewModel = koinViewModel()
        )
    }
    entry<AuthModuleRoute.LoginCode> {
        LoginCodeScreen(
            email = it.email,
            viewModel = koinViewModel()
        )
    }
}