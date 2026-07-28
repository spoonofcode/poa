package com.spoonofcode.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

val LocalNavViewModelStoreProvider = staticCompositionLocalOf<NavViewModelStoreProvider> {
    error("No NavViewModelStoreProvider provided")
}

class NavViewModelStoreProvider {
    private val stores = mutableMapOf<Any, ViewModelStore>()

    fun getStore(key: Any): ViewModelStore {
        return stores.getOrPut(key) { ViewModelStore() }
    }

    fun cleanUp(currentKeys: Set<Any>) {
        val iterator = stores.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key !in currentKeys) {
                entry.value.clear()
                iterator.remove()
            }
        }
    }
}

inline fun <reified T : NavKey> EntryProviderScope<NavKey>.viewModelEntry(
    crossinline content: @Composable (T) -> Unit
) {
    entry<T> { route ->
        val provider = LocalNavViewModelStoreProvider.current
        val store = provider.getStore(route)
        val owner = remember(store) {
            object : ViewModelStoreOwner {
                override val viewModelStore: ViewModelStore = store
            }
        }
        CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
            content(route)
        }
    }
}
