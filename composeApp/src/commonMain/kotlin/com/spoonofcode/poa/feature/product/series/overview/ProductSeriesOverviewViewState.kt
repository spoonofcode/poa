package com.spoonofcode.poa.feature.product.series.overview

import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState

internal data class ProductSeriesOverviewViewState(
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val productSeries: List<Product> = emptyList(),
    val checkedProductSeriesIds: Map<String, Boolean> = emptyMap(),
) : BaseViewState()