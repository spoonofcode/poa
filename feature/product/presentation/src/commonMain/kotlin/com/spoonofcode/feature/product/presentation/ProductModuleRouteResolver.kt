package com.spoonofcode.feature.product.presentation


import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.presentation.details.ProductDetailsScreen
import com.spoonofcode.feature.product.presentation.edit.ProductEditScreen
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewScreen
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.productModuleRouteResolver() {
    entry<ProductModuleRoute.ProductDetails> {
        ProductDetailsScreen(
            productId = it.productId,
            viewModel = koinViewModel(),
        )
    }

    entry<ProductModuleRoute.ProductEdit> {
        ProductEditScreen(
            productId = it.productId,
            viewModel = koinViewModel(),
        )
    }

    entry<ProductModuleRoute.ProductOverview> {
        ProductOverviewScreen(
            viewModel = koinViewModel(),
        )
    }

    entry<ProductModuleRoute.ProductSeriesOverview> {
        ProductSeriesOverviewScreen(
            viewModel = koinViewModel(),
        )
    }
}