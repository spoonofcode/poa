package com.spoonofcode.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun NavigationHandler(
    navigationFlow: SharedFlow<NavigationEvent>,
    navigator: Navigator
) {
    LaunchedEffect(true) {
        navigationFlow.collect { navigationEvent ->
            when (navigationEvent) {
                NavigationEvent.Pop -> navigator.pop()
                NavigationEvent.PopToRoot -> navigator.popUntilRoot()
                is NavigationEvent.PopUpTo<*> ->
                    navigator.popUntil { navigationEvent.screenClass.isInstance(it) }

                is NavigationEvent.Push -> navigator.push(navigationEvent.screen)
                is NavigationEvent.ReplaceAll -> navigator.replaceAll(navigationEvent.screens)
            }
        }
    }
}