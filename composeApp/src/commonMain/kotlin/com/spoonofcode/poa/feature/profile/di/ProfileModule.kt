package com.spoonofcode.poa.feature.profile.di

import com.spoonofcode.poa.feature.profile.ProfileViewModel
import com.spoonofcode.poa.feature.profile.edit.ProfileEditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ProfileEditViewModel)
}