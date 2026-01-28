package com.spoonofcode.poa.navigation

import com.spoonofcode.core.presentation.navigation.Screen

sealed class FAQModule : Screen() {
    object FAQScreen : FAQModule()
}