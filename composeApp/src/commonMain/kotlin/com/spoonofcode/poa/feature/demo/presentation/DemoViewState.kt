package com.spoonofcode.poa.feature.demo.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState

@Immutable
internal data class DemoViewState(
    val isLoading: Boolean = false,
) : BaseViewState()