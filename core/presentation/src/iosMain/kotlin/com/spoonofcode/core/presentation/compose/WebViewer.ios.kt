package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

// TODO #49 Fix iOS web view or test with real device
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MyWebView(
    url: String,
    modifier: Modifier
) {
    // UIKitView is an experimental API that lets you embed native iOS views in Compose.
    // Make sure your Compose Multiplatform version supports it.
    UIKitView(
        factory = {
            // Create a WKWebView as the native iOS view
            val config = WKWebViewConfiguration()
            val webView = WKWebView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0), configuration = config)

            // Load the URL (note: `loadRequest` takes an NSURLRequest)
            val nsUrl = NSURL.URLWithString("https://www.apple.com")
            if (nsUrl != null) {
                webView.loadRequest(NSURLRequest(nsUrl))
            }
            webView
        },
//        modifier = modifier,
//        properties = UIKitInteropProperties(
//            isInteractive = true,
//            isNativeAccessibilityEnabled = true
//        )
    )
}