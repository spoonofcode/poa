package com.spoonofcode.feature.profile.presentation.di

import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.profile.presentation.ProfileModuleRouteResolver
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsViewModel
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profilePresentationModule = module {
    factoryOf(::ProfileModuleRouteResolver) { bind<ModuleRouteResolver<ProfileModuleRoute>>() }

    viewModelOf(::ProfileDetailsViewModel)
    viewModelOf(::ProfileEditViewModel)
}