package com.spoonofcode.poa.feature.home

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class HomeViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
) : BaseViewState()