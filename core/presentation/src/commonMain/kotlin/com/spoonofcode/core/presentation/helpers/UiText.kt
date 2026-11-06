package com.spoonofcode.core.presentation.helpers

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class DynamicString(
        val value: String,
    ) : UiText

    data class Resource(
        val id: StringResource,
        val args: List<Any> = emptyList(),
    ) : UiText

    data object Empty : UiText
}

@Composable
fun UiText.asString(): String =
    when (this) {
        is UiText.DynamicString -> value
        is UiText.Resource -> {
            val resolvedArgs =
                args
                    .map { arg ->
                        if (arg is UiText) arg.asString() else arg
                    }.toTypedArray()

            stringResource(resource = id, formatArgs = resolvedArgs)
        }
        UiText.Empty -> ""
    }

suspend fun UiText.asStringAsync(): String =
    when (this) {
        is UiText.DynamicString -> value
        is UiText.Resource -> {
            runCatching {
                val resolvedArgs =
                    args
                        .map { arg ->
                            if (arg is UiText) arg.asStringAsync() else arg
                        }.toTypedArray()

                getString(resource = id, formatArgs = resolvedArgs)
            }.getOrElse { id.key }
        }
        UiText.Empty -> ""
    }
