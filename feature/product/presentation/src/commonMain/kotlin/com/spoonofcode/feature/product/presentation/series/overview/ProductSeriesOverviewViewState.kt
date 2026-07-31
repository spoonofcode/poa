package com.spoonofcode.feature.product.presentation.series.overview

import androidx.compose.runtime.Immutable
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.feature.partner.domain.model.ProductSeries

@Immutable
internal data class ProductSeriesOverviewViewState(
    val productSeries: List<ProductSeries>,
    val checkedProductSeriesNames: Set<String> = emptySet(),
) : BaseViewState()