package com.spoonofcode.poa.feature.home

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class HomeViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
) : BaseViewState(screenState)