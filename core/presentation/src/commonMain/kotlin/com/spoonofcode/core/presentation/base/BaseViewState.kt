package com.spoonofcode.core.presentation.base

sealed interface ViewState<out VS : BaseViewState> {
    data object Initial : ViewState<Nothing>
    data object Loading : ViewState<Nothing>
    data object Error : ViewState<Nothing>
    data class Content<VS : BaseViewState>(val data: VS) : ViewState<VS>
}

abstract class BaseViewState