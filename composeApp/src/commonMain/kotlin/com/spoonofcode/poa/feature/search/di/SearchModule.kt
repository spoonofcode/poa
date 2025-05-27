package com.spoonofcode.poa.feature.search.di

import com.spoonofcode.poa.feature.search.SearchViewModel
import com.spoonofcode.poa.feature.search.filter.FilterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::FilterViewModel)
}