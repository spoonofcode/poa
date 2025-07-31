package com.spoonofcode.poa.feature.product.di

import com.spoonofcode.poa.feature.product.details.ProductDetailsViewModel
import com.spoonofcode.poa.feature.product.overview.ProductOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productModule = module {
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::ProductOverviewViewModel)
//    viewModelOf(::ProductEditViewModel)
}