package com.spoonofcode.feature.appnavigation

sealed class ProductModuleRoute : ModuleRoute() {
    data class ProductDetails(
        val productId: String,
        val hasOwnerUser: Boolean = true,
    ) : ProductModuleRoute()

    data class ProductEdit(
        val productId: String,
    ) : ProductModuleRoute()
    object ProductOverview : ProductModuleRoute()
    object ProductSeriesOverview : ProductModuleRoute()
}