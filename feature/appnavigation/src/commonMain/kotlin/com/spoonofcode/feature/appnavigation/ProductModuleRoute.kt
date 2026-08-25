package com.spoonofcode.feature.appnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ProductModuleRoute : ModuleRoute() {
    @Serializable
    data class ProductDetails(
        val productId: String,
    ) : ProductModuleRoute()

    @Serializable
    data class ProductEdit(
        val productId: String,
    ) : ProductModuleRoute()

    @Serializable
    object ProductOverview : ProductModuleRoute()

    @Serializable
    object ProductSeriesOverview : ProductModuleRoute()
}