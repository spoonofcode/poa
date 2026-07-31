package com.spoonofcode.core.presentation.navigation

import com.spoonofcode.core.data.logging.POALogger
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.reflect.KClass

class ViewModelNavigatorImpl(
    private val poaLogger: POALogger,
) : ViewModelNavigator {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()

    override val navigationEvents: SharedFlow<NavigationEvent>
        get() = _navigationEvents.asSharedFlow()

    override suspend fun pop() = navigate(NavigationEvent.Pop)

    override suspend fun popToRoot() = navigate(NavigationEvent.PopToRoot)

    override suspend fun <T : Any> popUpTo(screenClass: KClass<T>) =
        navigate(NavigationEvent.PopUpTo(screenClass))

    override suspend fun push(screen: Any) = navigate(NavigationEvent.Push(screen))

    override suspend fun replaceAll(screens: List<Any>) =
        navigate(NavigationEvent.ReplaceAll(screens))

    private suspend fun navigate(event: NavigationEvent) {
        poaLogger.info("ViewModelNavigator: Navigating to $event")
        _navigationEvents.emit(event)
    }
}