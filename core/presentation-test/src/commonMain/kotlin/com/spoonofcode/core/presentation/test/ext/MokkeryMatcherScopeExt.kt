package com.spoonofcode.core.presentation.test.ext

import dev.mokkery.matcher.MokkeryMatcherScope
import dev.mokkery.matcher.matches
import kotlin.reflect.KClass

inline fun <reified T> MokkeryMatcherScope.containsTypes(
    vararg types: KClass<out Any>
): List<T> =
    matches(
        toString = {
            "containsExactlyTypes(${types.joinToString { it.simpleName ?: "?" }})"
        },
        predicate = { arg ->
            val list = arg as? List<*> ?: return@matches false
            if (list.isEmpty()) return@matches false

            val allItemsAllowed = list.all { item ->
                item != null && types.any { type -> type.isInstance(item) }
            }
            if (!allItemsAllowed) return@matches false

            val allTypesPresent = types.all { type ->
                list.any { item -> type.isInstance(item) }
            }
            allTypesPresent
        }
    )
