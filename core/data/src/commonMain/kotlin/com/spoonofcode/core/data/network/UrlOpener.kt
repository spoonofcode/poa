package com.spoonofcode.core.data.network

interface UrlOpener {
    fun openUrl(url: String)
}

expect fun getUrlOpener(): UrlOpener