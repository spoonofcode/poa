package com.spoonofcode.core.presentation.helpers

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable

@Serializable
sealed class UiTextArg {
    abstract fun value(): Any
    // all the types we need for stringRes parameters
    @Serializable data class StringArg(val v: String) : UiTextArg() { override fun value() = v }
    @Serializable data class IntArg(val v: Int) : UiTextArg() { override fun value() = v }
    @Serializable data class LongArg(val v: Long) : UiTextArg() { override fun value() = v }
    @Serializable data class FloatArg(val v: Float) : UiTextArg() { override fun value() = v }
    @Serializable data class DoubleArg(val v: Double) : UiTextArg() { override fun value() = v }
}

fun String.toUiArg(): UiTextArg = UiTextArg.StringArg(this)
fun Int.toUiArg(): UiTextArg = UiTextArg.IntArg(this)
fun Long.toUiArg(): UiTextArg = UiTextArg.LongArg(this)
fun Float.toUiArg(): UiTextArg = UiTextArg.FloatArg(this)
fun Double.toUiArg(): UiTextArg = UiTextArg.DoubleArg(this)

@Serializable
sealed interface UiText {
    @Serializable
    data class DynamicString(val value: String) : UiText

    @Serializable
    data class StringResource(
        @StringRes val id: Int,
        val args: List<UiTextArg> = emptyList()
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, *args.map { it.value() }.toTypedArray())
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args.map { it.value() }.toTypedArray())
        }
    }
}