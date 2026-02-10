package com.spoonofcode.core.presentation.web

interface UrlOpener {
    fun openUrl(url: String)
}

expect fun getUrlOpener(): UrlOpener