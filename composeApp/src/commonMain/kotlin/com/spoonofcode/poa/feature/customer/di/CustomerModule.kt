package com.spoonofcode.poa.feature.customer.di

import com.spoonofcode.poa.feature.customer.overview.CustomerOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val customerModule = module {
    viewModelOf(::CustomerOverviewViewModel)
}