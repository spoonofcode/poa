package com.spoonofcode.feature.appnavigation

sealed class TACModule : Screen() {
    object TACScreen : TACModule()
}