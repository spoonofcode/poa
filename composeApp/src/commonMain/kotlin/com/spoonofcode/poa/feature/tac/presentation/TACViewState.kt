package com.spoonofcode.poa.feature.tac.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState

@Immutable
internal data class TACViewState(
    val test: String = ""
) : BaseViewState()