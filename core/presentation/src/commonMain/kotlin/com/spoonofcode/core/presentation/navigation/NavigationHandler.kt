package com.spoonofcode.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun NavigationHandler(
    navigationFlow: SharedFlow<NavigationEvent>,
    backStack: NavBackStack<NavKey>
) {
    LaunchedEffect(true) {
        navigationFlow.collect { navigationEvent ->
            when (navigationEvent) {
                NavigationEvent.Pop -> {
                    if (backStack.size > 1) {
                        backStack.removeLast()
                    }
                }
                NavigationEvent.PopToRoot -> {
                    val root = backStack.firstOrNull()
                    backStack.clear()
                    root?.let { backStack.add(it) }
                }

                is NavigationEvent.PopUpTo<*> -> {
                    val index = backStack.indexOfLast { navigationEvent.screenClass.isInstance(it) }
                    if (index != -1) {
                        while (backStack.size > index + 1) {
                            backStack.removeLast()
                        }
                    }
                }

                is NavigationEvent.Push -> {
                    (navigationEvent.screen as? NavKey)?.let {
                        backStack.add(it)
                    }
                }

                is NavigationEvent.ReplaceAll -> {
                    val routes = navigationEvent.screens.filterIsInstance<NavKey>()
                    if (routes.isNotEmpty()) {
                        backStack.clear()
                        backStack.addAll(routes)
                    }
                }
            }
        }
    }
}
