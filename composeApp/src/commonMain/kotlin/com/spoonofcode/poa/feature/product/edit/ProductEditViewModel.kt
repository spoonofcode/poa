package com.spoonofcode.poa.feature.product.edit

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class ProductEditViewModel(
) : BaseViewModel<ProductEditViewState>(ProductEditViewState()) {

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