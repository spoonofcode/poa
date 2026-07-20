package com.spoonofcode.feature.auth.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.authModuleRouteResolver() {
    entry<AuthModuleRoute.Login> {
        LoginScreen(
            viewModel = koinViewModel()
        ).Content()
    }
    entry<AuthModuleRoute.LoginCode> {
        LoginCodeScreen(
            email = it.email,
            viewModel = koinViewModel()
        ).Content()
    }
}