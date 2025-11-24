package com.spoonofcode.poa.feature.demo

sealed interface DemoViewAction {
    data object InitView : DemoViewAction
}