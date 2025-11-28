package com.spoonofcode.poa.core.ext

import dev.mokkery.matcher.MokkeryMatcherScope
import dev.mokkery.matcher.matches
import kotlin.reflect.KClass

inline fun <reified T> MokkeryMatcherScope.containsType() =
    matches<List<T>> { list -> list.any { true } }


inline fun <reified T> MokkeryMatcherScope.containsTypes(vararg types: KClass<T>): List<T> =
    matches(
        toString = { "containsTypes(${types.joinToString { it.simpleName ?: "?" }})" },
        predicate = { arg ->
            val list = arg as? List<T> ?: return@matches false
            types.all { type -> list.any { item -> type.isInstance(item) } }
        }
    )
