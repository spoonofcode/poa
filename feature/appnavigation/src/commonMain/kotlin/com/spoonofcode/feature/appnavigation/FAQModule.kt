package com.spoonofcode.feature.appnavigation

sealed class FAQModule : Screen() {
    object FAQScreen : FAQModule()
}