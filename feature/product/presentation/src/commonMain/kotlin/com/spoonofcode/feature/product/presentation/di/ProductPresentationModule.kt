package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.product.presentation.ProductModuleRouteResolver
import com.spoonofcode.feature.product.presentation.details.ProductDetailsViewModel
import com.spoonofcode.feature.product.presentation.edit.ProductEditViewModel
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewViewModel
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productPresentationModule = module {
    factoryOf(::ProductModuleRouteResolver) { bind<ModuleRouteResolver<ProductModuleRoute>>() }

    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::ProductEditViewModel)
    viewModelOf(::ProductOverviewViewModel)
    viewModelOf(::ProductSeriesOverviewViewModel)
}