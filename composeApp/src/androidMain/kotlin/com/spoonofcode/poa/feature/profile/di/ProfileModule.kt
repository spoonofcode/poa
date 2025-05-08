package com.spoonofcode.poa.feature.profile.di

import com.spoonofcode.poa.feature.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val profileModule = module {
    viewModelOf(::ProfileViewModel)
}