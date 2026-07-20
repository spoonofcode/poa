package com.spoonofcode.poa

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.AppModuleRoute
import com.spoonofcode.poa.app.MainHostScreen

fun EntryProviderScope<NavKey>.appModuleRouteResolver() {
    entry<AppModuleRoute.MainHost> {
        MainHostScreen(
        )
    }
}