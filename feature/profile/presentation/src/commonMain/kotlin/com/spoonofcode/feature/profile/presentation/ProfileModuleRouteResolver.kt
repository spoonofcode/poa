package com.spoonofcode.feature.profile.presentation

import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.route.ModuleRouteResolver
import com.spoonofcode.feature.appnavigation.ProfileModule
import com.spoonofcode.feature.profile.presentation.details.ProfileDetailsScreen
import com.spoonofcode.feature.profile.presentation.edit.ProfileEditScreen

class ProfileModuleRouteResolver : ModuleRouteResolver<ProfileModule> {
    override fun resolve(screen: com.spoonofcode.feature.appnavigation.Screen): Screen? =
        (screen as? ProfileModule)?.let {
            when (it) {
                ProfileModule.ProfileDetailsScreen -> ProfileDetailsScreen()
                ProfileModule.ProfileEditScreen -> ProfileEditScreen()
            }
        }
}