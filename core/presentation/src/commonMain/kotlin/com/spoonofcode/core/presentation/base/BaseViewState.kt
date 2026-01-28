package com.spoonofcode.core.presentation.base

data class ViewState<VS : BaseViewState>(
    val data: VS,
    val screenState: ScreenState = ScreenState.LOADING
)

abstract class BaseViewState

enum class ScreenState {
    LOADING,
    ERROR,
    CONTENT,
}