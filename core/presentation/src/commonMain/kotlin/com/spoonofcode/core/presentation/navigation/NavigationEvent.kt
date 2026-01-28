package com.spoonofcode.core.presentation.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlin.reflect.KClass

sealed class NavigationEvent {
    data object Pop : NavigationEvent()
    data object PopToRoot : NavigationEvent()
    data class PopUpTo<T: Screen>(val screenClass: KClass<T>) : NavigationEvent()
    data class Push(val screen: Screen) : NavigationEvent()
    data class ReplaceAll(val screens: List<Screen>) : NavigationEvent()
}