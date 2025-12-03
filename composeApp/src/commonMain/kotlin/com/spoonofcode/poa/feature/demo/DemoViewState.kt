package com.spoonofcode.poa.feature.demo

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class DemoViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val isLoading: Boolean = false,
) : BaseViewState(screenState)