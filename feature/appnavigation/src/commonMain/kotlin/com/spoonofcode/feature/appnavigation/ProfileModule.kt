package com.spoonofcode.feature.appnavigation

sealed class ProfileModule : Screen() {
    object ProfileDetailsScreen : ProfileModule()
    object ProfileEditScreen : ProfileModule()
    object FAQScreen : ProfileModule()
}