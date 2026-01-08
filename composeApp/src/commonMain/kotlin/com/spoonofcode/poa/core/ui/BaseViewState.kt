package com.spoonofcode.poa.core.ui

data class ViewState<VS : BaseViewState>(
    val data: VS,
    val screenState: ScreenState = ScreenState.CONTENT
)

abstract class BaseViewState

enum class ScreenState {
    LOADING,
    ERROR,
    CONTENT,
}