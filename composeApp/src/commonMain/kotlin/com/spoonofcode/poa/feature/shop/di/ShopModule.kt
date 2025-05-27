package com.spoonofcode.poa.feature.shop.di

import com.spoonofcode.poa.feature.shop.ShopViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shopModule = module {
    viewModelOf(::ShopViewModel)
}