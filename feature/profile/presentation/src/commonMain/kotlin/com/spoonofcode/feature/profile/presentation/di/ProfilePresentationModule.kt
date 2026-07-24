package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profilePresentationModule = module {
    viewModelOf(::ProfileDetailsViewModel)
    viewModelOf(::ProfileEditViewModel)
}