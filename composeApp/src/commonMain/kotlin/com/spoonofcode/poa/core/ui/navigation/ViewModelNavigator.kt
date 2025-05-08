package com.spoonofcode.poa.core.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.reflect.KClass

class ViewModelNavigator {

    val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    suspend fun pop() = navigate(NavigationEvent.Pop)

    suspend fun popToRoot() = navigate(NavigationEvent.PopToRoot)

    suspend fun <T : Screen> popUpTo(screenClass: KClass<T>) =
        navigate(NavigationEvent.PopUpTo(screenClass))

    suspend fun push(screen: Screen) = navigate(NavigationEvent.Push(screen))

    suspend fun replaceAll(screens: List<Screen>) = navigate(NavigationEvent.ReplaceAll(screens))

    private suspend fun navigate(event: NavigationEvent) = _navigationEvents.emit(event)

}