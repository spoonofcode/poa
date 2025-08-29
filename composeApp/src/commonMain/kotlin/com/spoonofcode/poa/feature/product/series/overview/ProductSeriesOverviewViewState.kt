package com.spoonofcode.poa.feature.product.series.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.core.model.Product
import com.spoonofcode.poa.core.ui.BaseViewState
import com.spoonofcode.poa.core.ui.ScreenState

@Immutable
internal data class ProductSeriesOverviewViewState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val productSeries: List<Product> = emptyList(),
    val checkedProductSeriesIds: Map<String, Boolean> = emptyMap(),
) : BaseViewState(screenState)