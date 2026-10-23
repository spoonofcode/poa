package com.spoonofcode.feature.profile.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.navigation.viewModelEntry
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsScreen
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.profileModuleRouteResolver() {
    viewModelEntry<ProfileModuleRoute.ProfileDetails> {
        ProfileDetailsScreen(
            viewModel = koinViewModel(),
        )
    }

    viewModelEntry<ProfileModuleRoute.ProfileEdit> {
        ProfileEditScreen(
            viewModel = koinViewModel(),
        )
    }
}
