package com.spoonofcode.poa.feature.scan

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ScanViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()