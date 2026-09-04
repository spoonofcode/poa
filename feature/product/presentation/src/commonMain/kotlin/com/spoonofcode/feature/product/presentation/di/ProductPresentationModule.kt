package com.spoonofcode.feature.product.presentation.di

import com.spoonofcode.feature.product.presentation.ProductEventBus
import com.spoonofcode.feature.product.presentation.details.ProductDetailsViewModel
import com.spoonofcode.feature.product.presentation.edit.ProductEditViewModel
import com.spoonofcode.feature.product.presentation.overview.ProductOverviewViewModel
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productPresentationModule = module {
    singleOf(::ProductEventBus)
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::ProductEditViewModel)
    viewModelOf(::ProductOverviewViewModel)
    viewModelOf(::ProductSeriesOverviewViewModel)
}