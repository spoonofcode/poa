package com.spoonofcode.feature.product.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.presentation.details.ProductDetailsScreen
import com.spoonofcode.feature.product.presentation.edit.ProductEditScreen
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewScreen
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.productModuleRouteResolver() {
    viewModelEntry<ProductModuleRoute.ProductDetails> {
        ProductDetailsScreen(
            viewModel = koinViewModel(parameters = { parametersOf(it.productId) }),
        )
    }

    viewModelEntry<ProductModuleRoute.ProductEdit> {
        ProductEditScreen(
            viewModel = koinViewModel(parameters = { parametersOf(it.productId) }),
        )
    }

    viewModelEntry<ProductModuleRoute.ProductOverview> {
        ProductOverviewScreen(
            viewModel = koinViewModel(),
        )
    }

    viewModelEntry<ProductModuleRoute.ProductSeriesOverview> {
        ProductSeriesOverviewScreen(
            viewModel = koinViewModel(),
        )
    }
}
