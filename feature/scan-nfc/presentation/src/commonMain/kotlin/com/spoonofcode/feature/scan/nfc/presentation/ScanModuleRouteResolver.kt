package com.spoonofcode.feature.scan.nfc.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.ScanModuleRoute
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.scanModuleRouteResolver() {
    entry<ScanModuleRoute.Scan> {
        ScanScreen(
            viewModel = koinViewModel()
        ).Content()
    }
}