package com.spoonofcode.feature.auth.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.auth.presentation.login.LoginScreen
import com.spoonofcode.feature.auth.presentation.logincode.LoginCodeScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.authModuleRouteResolver() {
    viewModelEntry<AuthModuleRoute.Login> {
        LoginScreen(
            viewModel = koinViewModel(),
        )
    }
    viewModelEntry<AuthModuleRoute.LoginCode> {
        LoginCodeScreen(
            viewModel =
                koinViewModel(
                    parameters = { parametersOf(it.email) },
                ),
        )
    }
}
