package com.spoonofcode.feature.faq.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.FAQModuleRoute
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.faqModuleRouteResolver() {
    entry<FAQModuleRoute.FAQ> {
        FAQScreen(
            viewModel = koinViewModel()
        )
    }
}