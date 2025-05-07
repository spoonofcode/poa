package com.spoonofcode.poa.core.ui.di

import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
    singleOf(::ViewModelNavigator)
}