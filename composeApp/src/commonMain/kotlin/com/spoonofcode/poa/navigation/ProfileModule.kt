package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class ProfileModule : Screen() {
    object ProfileDetailsScreen : ProfileModule()
    object ProfileEditScreen : ProfileModule()
}