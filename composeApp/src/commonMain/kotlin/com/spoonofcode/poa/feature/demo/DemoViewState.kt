package com.spoonofcode.poa.feature.demo

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class DemoViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
) : BaseViewState(screenState)