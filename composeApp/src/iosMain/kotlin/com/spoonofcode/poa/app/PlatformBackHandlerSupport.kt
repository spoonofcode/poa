package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.HostDefaultKey
import androidx.compose.runtime.HostDefaultProvider
import androidx.compose.runtime.LocalHostDefaultProvider
import androidx.compose.runtime.remember
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner

@Composable
actual fun PlatformBackHandlerSupport(content: @Composable () -> Unit) {
    val hostDefaultProvider =
        remember {
            object : HostDefaultProvider {
                @Suppress("UNCHECKED_CAST")
                override fun <T> getHostDefault(key: HostDefaultKey<T>): T = null as T
            }
        }

    val navigationEventDispatcherOwner =
        remember {
            object : NavigationEventDispatcherOwner {
                override val navigationEventDispatcher = NavigationEventDispatcher()
            }
        }

    CompositionLocalProvider(
        LocalHostDefaultProvider provides hostDefaultProvider,
        LocalNavigationEventDispatcherOwner provides navigationEventDispatcherOwner,
    ) {
        content()
    }
}
