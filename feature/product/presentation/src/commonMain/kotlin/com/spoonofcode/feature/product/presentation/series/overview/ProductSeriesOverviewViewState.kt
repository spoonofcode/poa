package com.spoonofcode.feature.product.presentation.series.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.feature.product.domain.model.Product
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.product.domain.model.ProductSeries

@Immutable
internal data class ProductSeriesOverviewViewState(
    val productSeries: List<ProductSeries> = emptyList(),
    val checkedProductSeriesIds: Set<String> = emptySet(),
) : BaseViewState()