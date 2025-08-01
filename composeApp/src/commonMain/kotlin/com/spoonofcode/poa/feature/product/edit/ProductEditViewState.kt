package com.spoonofcode.poa.feature.product.edit

import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProductEditViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()