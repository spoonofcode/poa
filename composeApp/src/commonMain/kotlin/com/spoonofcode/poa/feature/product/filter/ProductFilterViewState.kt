package com.spoonofcode.poa.feature.product.filter

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProductFilterViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val selectedName: String = "",
    val selectedCollectionName: String = "",
) : BaseViewState() {
    companion object Companion {
        const val ALL_OPTION_ID = 0
        const val ALL_OPTION_NAME = "All"
        const val ONE_WEEK_IN_HOURS = 168
    }
}
