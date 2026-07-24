package com.spoonofcode.core.presentation.navigation

import kotlin.reflect.KClass

sealed class NavigationEvent {
    data object Pop : NavigationEvent()
    data object PopToRoot : NavigationEvent()
    data class PopUpTo<T: Any>(val screenClass: KClass<T>) : NavigationEvent()
    data class Push(val screen: Any) : NavigationEvent()
    data class ReplaceAll(val screens: List<Any>) : NavigationEvent()
}