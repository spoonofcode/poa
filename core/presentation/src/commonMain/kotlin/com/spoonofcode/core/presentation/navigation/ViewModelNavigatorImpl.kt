package com.spoonofcode.core.presentation.navigation

import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.reflect.KClass

class ViewModelNavigatorImpl : ViewModelNavigator {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()

    override val navigationEvents: SharedFlow<NavigationEvent>
        get() = _navigationEvents.asSharedFlow()

    override suspend fun pop() = navigate(NavigationEvent.Pop)

    override suspend fun popToRoot() = navigate(NavigationEvent.PopToRoot)

    override suspend fun <T : Screen> popUpTo(screenClass: KClass<T>) =
        navigate(NavigationEvent.PopUpTo(screenClass))

    override suspend fun push(screen: Screen) = navigate(NavigationEvent.Push(screen))

    override suspend fun replaceAll(screens: List<Screen>) =
        navigate(NavigationEvent.ReplaceAll(screens))

    private suspend fun navigate(event: NavigationEvent) = _navigationEvents.emit(event)

}