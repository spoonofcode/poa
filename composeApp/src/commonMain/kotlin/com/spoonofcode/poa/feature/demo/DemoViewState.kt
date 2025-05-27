package com.spoonofcode.poa.feature.demo

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class DemoViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()