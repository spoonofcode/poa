package com.spoonofcode.poa.feature.demo.presentation

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class DemoViewState(
    val isLoading: Boolean = false,
) : BaseViewState()