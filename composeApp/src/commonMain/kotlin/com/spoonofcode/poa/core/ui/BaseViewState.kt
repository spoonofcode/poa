package com.spoonofcode.poa.core.ui

abstract class BaseViewState(
    open val screenState: ScreenState = ScreenState.CONTENT
)

enum class ScreenState {
    INITIAL,
    LOADING,
    ERROR,
    CONTENT,
}