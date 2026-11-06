package com.spoonofcode.core.presentation.web

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IosUrlOpener : UrlOpener {
    override fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        nsUrl?.let {
            if (UIApplication.sharedApplication.canOpenURL(it)) {
                UIApplication.sharedApplication.openURL(it, options = emptyMap<Any?, Any?>(), completionHandler = null)
            }
        }
    }
}

actual fun getUrlOpener(): UrlOpener = IosUrlOpener()
