package com.spoonofcode.poa.feature.partner.overview

import com.spoonofcode.poa.core.ui.BaseViewModel

internal class PartnerOverviewViewModel(
) : BaseViewModel<PartnerOverviewViewState>(PartnerOverviewViewState()) {

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