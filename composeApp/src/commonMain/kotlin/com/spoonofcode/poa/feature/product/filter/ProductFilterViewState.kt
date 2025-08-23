package com.spoonofcode.poa.feature.product.filter

import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

internal data class ProductFilterViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val selectedName: String = "",
    val selectedCollectionName: String = "",
) : BaseViewState(screenState) {
    companion object Companion {
        const val ALL_OPTION_ID = 0
        const val ALL_OPTION_NAME = "All"
    }
}
