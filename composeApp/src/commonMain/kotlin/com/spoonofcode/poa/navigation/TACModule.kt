package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class TACModule : Screen() {
    object TACScreen : TACModule()
}