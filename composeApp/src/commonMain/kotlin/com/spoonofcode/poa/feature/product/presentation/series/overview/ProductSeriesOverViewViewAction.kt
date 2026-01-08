package com.spoonofcode.poa.feature.product.presentation.series.overview

import com.spoonofcode.poa.core.ui.BaseViewAction

sealed interface ProductSeriesOverViewViewAction : BaseViewAction {
    data class CheckedProductSeries(val productSeriesId: String) : ProductSeriesOverViewViewAction
    data object InitView : ProductSeriesOverViewViewAction
    data object NavigateToNotification : ProductSeriesOverViewViewAction
}