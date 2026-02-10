package com.spoonofcode.feature.appnavigation

sealed class ScanModule : Screen() {
    object ScanScreen : ScanModule()
}