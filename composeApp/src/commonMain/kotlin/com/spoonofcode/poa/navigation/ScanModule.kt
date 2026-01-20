package com.spoonofcode.poa.navigation

sealed class ScanModule : Screen() {
    object ScanScreen : ScanModule()
}