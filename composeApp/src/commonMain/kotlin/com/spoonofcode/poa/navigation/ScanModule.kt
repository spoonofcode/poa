package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class ScanModule : Screen() {
    object ScanScreen : ScanModule()
}