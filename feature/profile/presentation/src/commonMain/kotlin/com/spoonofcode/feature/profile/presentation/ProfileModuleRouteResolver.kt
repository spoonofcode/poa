package com.spoonofcode.feature.profile.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsScreen
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.profileModuleRouteResolver() {
    entry<ProfileModuleRoute.ProfileDetails> {
        ProfileDetailsScreen(
            viewModel = koinViewModel()
        ).Content()
    }

    entry<ProfileModuleRoute.ProfileEdit> {
        ProfileEditScreen(
            viewModel = koinViewModel()
        ).Content()
    }
}