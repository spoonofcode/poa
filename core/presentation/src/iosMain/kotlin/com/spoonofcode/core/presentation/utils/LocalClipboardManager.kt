package com.spoonofcode.core.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import platform.UIKit.UIPasteboard

actual val LocalClipboardManager: ProvidableCompositionLocal<ClipboardManager?>
    @Composable get() = compositionLocalOf<ClipboardManager?> {
        object : ClipboardManager {
            override fun setText(text: String) {
                UIPasteboard.generalPasteboard.setString(text)
            }
        }
    }