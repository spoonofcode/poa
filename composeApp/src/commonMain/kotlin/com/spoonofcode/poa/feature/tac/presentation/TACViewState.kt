package com.spoonofcode.poa.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class TACViewState(
    val test: String = ""
) : BaseViewState()