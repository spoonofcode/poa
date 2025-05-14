package com.spoonofcode.poa.feature.search.di

import com.spoonofcode.poa.feature.search.SearchViewModel
import com.spoonofcode.poa.feature.search.filter.FilterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val searchModule = module {
    singleOf(::SearchViewModel)
    singleOf(::FilterViewModel)
}