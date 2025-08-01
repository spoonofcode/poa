package com.spoonofcode.poa.feature.scan

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class ScanViewModel(
) : BaseViewModel<ScanViewState>(ScanViewState()) {

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