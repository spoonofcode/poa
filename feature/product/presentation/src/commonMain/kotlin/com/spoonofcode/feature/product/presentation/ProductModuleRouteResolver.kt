package com.spoonofcode.feature.product.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.product.presentation.details.ProductDetailsScreen
import com.spoonofcode.feature.product.presentation.edit.ProductEditScreen
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewScreen
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewScreen

class ProductModuleRouteResolver : ModuleRouteResolver<ProductModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? ProductModule)?.let {
            when (it) {
                is ProductModule.ProductDetailsScreen -> ProductDetailsScreen(productId = it.productId)
                is ProductModule.ProductEditScreen -> ProductEditScreen(productId = it.productId)
                ProductModule.ProductOverviewScreen -> ProductOverviewScreen()
                ProductModule.ProductSeriesOverviewScreen -> ProductSeriesOverviewScreen()
            }
        }
}