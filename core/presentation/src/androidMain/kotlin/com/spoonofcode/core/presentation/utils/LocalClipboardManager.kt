package com.spoonofcode.core.presentation.utils

import android.content.ClipData
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

actual val LocalClipboardManager: ProvidableCompositionLocal<ClipboardManager?>
    @Composable
    get() {
        val context = LocalContext.current
        return staticCompositionLocalOf {
            object : ClipboardManager {
                override fun setText(text: String) {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                    val clip = ClipData.newPlainText("label", text)
                    clipboard.setPrimaryClip(clip)
                }
            }
        }
    }