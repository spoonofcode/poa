package com.spoonofcode.feature.tac.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.tacModuleRouteResolver() {
    viewModelEntry<TACModuleRoute.TAC> {
        TACScreen(
            backNavigationEnable = it.backNavigationEnabled,
            viewModel = koinViewModel(),
        )
    }
}
