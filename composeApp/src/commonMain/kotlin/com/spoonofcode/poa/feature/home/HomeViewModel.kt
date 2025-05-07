package com.spoonofcode.poa.feature.home

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class HomeViewModel(
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        initView()
    }

    fun initView() {
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