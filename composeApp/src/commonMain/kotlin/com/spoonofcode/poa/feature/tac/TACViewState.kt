package com.spoonofcode.poa.feature.tac

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class TACViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
) : BaseViewState(screenState)