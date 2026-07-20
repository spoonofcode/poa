package com.spoonofcode.poa

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

fun EntryProviderScope<NavKey>.appModuleRouteResolver() {
    // MainHost is no longer used, we use MainAppScreen as host directly in App.kt
}
