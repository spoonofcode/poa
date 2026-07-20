package com.spoonofcode.core.presentation.navigation

import kotlinx.coroutines.flow.SharedFlow
import kotlin.reflect.KClass

interface ViewModelNavigator {
    val navigationEvents: SharedFlow<NavigationEvent>

    suspend fun pop()
    suspend fun popToRoot()
    suspend fun <T : Any> popUpTo(screenClass: KClass<T>)
    suspend fun push(screen: Any)
    suspend fun replaceAll(screens: List<Any>)
}
