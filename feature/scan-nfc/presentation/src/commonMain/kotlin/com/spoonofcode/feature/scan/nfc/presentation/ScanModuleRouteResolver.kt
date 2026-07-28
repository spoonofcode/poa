package com.spoonofcode.feature.scan.nfc.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.ScanModuleRoute
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.scanModuleRouteResolver() {
    viewModelEntry<ScanModuleRoute.Scan> {
        ScanScreen(
            viewModel = koinViewModel()
        )
    }
}
