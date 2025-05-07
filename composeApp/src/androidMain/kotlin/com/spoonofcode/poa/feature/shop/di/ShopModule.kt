package com.spoonofcode.poa.feature.shop.di

import com.spoonofcode.poa.feature.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val shopModule = module {
    viewModelOf(::ShopViewModel)
}