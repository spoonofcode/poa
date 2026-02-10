package com.spoonofcode.feature.appnavigation

sealed class ProductModule : Screen() {
    data class ProductDetailsScreen(
        val productId: String,
        val hasOwnerUser: Boolean = true,
    ) : ProductModule()

    data class ProductEditScreen(
        val productId: String,
    ) : ProductModule()
    object ProductOverviewScreen : ProductModule()
    object ProductSeriesOverviewScreen : ProductModule()
}