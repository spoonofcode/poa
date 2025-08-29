package com.spoonofcode.poa.feature.home

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class HomeViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
) : BaseViewState(screenState)