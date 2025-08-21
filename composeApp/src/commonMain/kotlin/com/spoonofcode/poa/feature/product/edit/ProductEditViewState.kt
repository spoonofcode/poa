package com.spoonofcode.poa.feature.product.edit

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class ProductEditViewState(
    override val screenState: ScreenState = ScreenState.CONTENT,
    val text: String = ""
) : BaseViewState(screenState)