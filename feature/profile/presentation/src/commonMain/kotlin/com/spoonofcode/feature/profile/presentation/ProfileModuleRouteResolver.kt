package com.spoonofcode.feature.profile.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsScreen
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen

class ProfileModuleRouteResolver : ModuleRouteResolver<ProfileModuleRoute> {
    override fun resolve(moduleRoute: ModuleRoute): Screen? =
        (moduleRoute as? ProfileModuleRoute)?.let {
            when (it) {
                ProfileModuleRoute.ProfileDetails -> ProfileDetailsScreen()
                ProfileModuleRoute.ProfileEdit -> ProfileEditScreen()
            }
        }
}