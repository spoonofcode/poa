package com.spoonofcode.poa.feature.demo

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class DemoViewModel() : BaseViewModel<DemoViewState>(DemoViewState()) {

    fun initView() {
        viewModelScope.launch {
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}