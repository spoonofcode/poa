package com.spoonofcode.poa.navigation

sealed class ProfileModule : Screen() {
    object ProfileDetailsScreen : ProfileModule()
    object ProfileEditScreen : ProfileModule()
}