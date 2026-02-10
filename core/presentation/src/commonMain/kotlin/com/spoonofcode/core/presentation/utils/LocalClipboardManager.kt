package com.spoonofcode.core.presentation.utils

import androidx.compose.runtime.ProvidableCompositionLocal

interface ClipboardManager {
    fun setText(text: String)
}

expect val LocalClipboardManager: ProvidableCompositionLocal<ClipboardManager?>