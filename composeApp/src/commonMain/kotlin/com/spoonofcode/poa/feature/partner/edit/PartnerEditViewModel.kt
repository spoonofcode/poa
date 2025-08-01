package com.spoonofcode.poa.feature.partner.edit

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class PartnerEditViewModel(
) : BaseViewModel<PartnerEditViewState>(PartnerEditViewState()) {

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