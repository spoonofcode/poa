package com.spoonofcode.poa.feature.profile.di

import com.spoonofcode.poa.feature.profile.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val profileModule = module {
    singleOf(::ProfileViewModel)
}