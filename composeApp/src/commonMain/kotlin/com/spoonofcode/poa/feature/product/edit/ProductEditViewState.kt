package com.spoonofcode.poa.feature.product.edit

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProductEditViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val text: String = ""
) : BaseViewState(screenState)