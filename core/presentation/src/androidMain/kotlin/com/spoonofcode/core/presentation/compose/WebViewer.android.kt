package com.spoonofcode.core.presentation.compose

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun MyWebView(
    url: String,
    modifier: Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                // Basic settings
                settings.javaScriptEnabled = true

                // Optional: handle navigation within this WebView
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()

                // Load the requested URL
                loadUrl(url)
            }
        }
    )
}