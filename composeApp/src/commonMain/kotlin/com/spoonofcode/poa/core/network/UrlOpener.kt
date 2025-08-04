package com.spoonofcode.poa.core.network

interface UrlOpener {
    fun openUrl(url: String)
}

expect fun getUrlOpener(): UrlOpener