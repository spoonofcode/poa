package com.spoonofcode.poa.feature.scan

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class ScanViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
) : BaseViewState(screenState)