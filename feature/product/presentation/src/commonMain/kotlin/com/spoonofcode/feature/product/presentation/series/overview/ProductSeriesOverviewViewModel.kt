package com.spoonofcode.feature.product.presentation.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.feature.product.domain.usecase.GetProductSeriesUseCase
import kotlinx.coroutines.launch

internal class ProductSeriesOverviewViewModel(
    private val getProductSeriesUseCase: GetProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState, ProductSeriesOverViewViewAction>(
    ProductSeriesOverviewViewState()
) {

    override fun onAction(action: ProductSeriesOverViewViewAction) {
        when (action) {
            is ProductSeriesOverViewViewAction.CheckedProductSeries -> checkedProductSeries(action.productSeriesId)
            ProductSeriesOverViewViewAction.InitView -> initView()
            ProductSeriesOverViewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getProductSeriesUseCase()
                .onSuccess {
                    showContentView {
                        ProductSeriesOverviewViewState(
                            productSeries = it,
                        )
                    }
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun checkedProductSeries(productSeriesId: String) {
        val selected = currentState().checkedProductSeriesIds.toMutableSet()
        val wasAdded = selected.contains(productSeriesId)

        if (!wasAdded && selected.size >= MAX_SELECTED_PRODUCT_SERIES) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_PRODUCT_SERIES items.")
            )
            return
        }

        if (wasAdded) selected.remove(productSeriesId) else selected.add(productSeriesId)

        updateState { ProductSeriesOverviewViewState(checkedProductSeriesIds = selected) }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen(
                    topics = getSelectedProductSeries(),
                    itemIds = getSelectedProductSeriesNames()
                )
            )
        }
    }

    private fun getSelectedProductSeries(): List<String> =
        currentState().checkedProductSeriesIds.map { serieId -> "productSerieId$serieId" }.toList()

    private fun getSelectedProductSeriesNames(): List<String> =
        currentState().productSeries.filter { "${it.id}" in currentState().checkedProductSeriesIds }
            .map { productSeries -> productSeries.name }.toList()

    private companion object {
        private const val MAX_SELECTED_PRODUCT_SERIES = 5
    }
}