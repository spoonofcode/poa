package com.spoonofcode.poa.feature.product.presentation.series.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.poa.feature.product.domain.model.Product
import com.spoonofcode.poa.core.ui.base.BaseViewState

@Immutable
internal data class ProductSeriesOverviewViewState(
    val productSeries: List<Product> = emptyList(),
    val checkedProductSeriesIds: Set<String> = emptySet(),
) : BaseViewState()