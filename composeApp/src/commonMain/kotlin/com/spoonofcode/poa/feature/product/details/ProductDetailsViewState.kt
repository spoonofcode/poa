package com.spoonofcode.poa.feature.product.details

import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProductDetailsViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val product: Product? = null,
) : BaseViewState(
    isLoadingView = isLoadingView,
)