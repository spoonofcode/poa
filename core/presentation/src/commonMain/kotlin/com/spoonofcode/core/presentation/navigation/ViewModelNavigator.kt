package com.spoonofcode.core.presentation.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.flow.SharedFlow
import kotlin.reflect.KClass

interface ViewModelNavigator {
    val navigationEvents: SharedFlow<NavigationEvent>

    suspend fun pop()
    suspend fun popToRoot()
    suspend fun <T : Screen> popUpTo(screenClass: KClass<T>)
    suspend fun push(screen: Screen)
    suspend fun replaceAll(screens: List<Screen>)
}
