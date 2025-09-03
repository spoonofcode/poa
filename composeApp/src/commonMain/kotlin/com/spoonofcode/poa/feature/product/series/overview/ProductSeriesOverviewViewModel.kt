package com.spoonofcode.poa.feature.product.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetProductSeriesUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductSeriesOverviewViewModel(
    private val getProductSeriesUseCase: GetProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState>(ProductSeriesOverviewViewState()) {

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val productSeries = getProductSeriesUseCase()
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        productSeries = productSeries,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun checkedProductSeries(productSeriesId: String) {
        val selected = currentState().checkedProductSeriesIds.toMutableSet()
        val wasAdded = selected.contains(productSeriesId)

        if (!wasAdded && selected.size >= MAX_SELECTED_PRODUCT_SERIES) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_PRODUCT_SERIES items.")
            )
            return
        }

        if (wasAdded) selected.remove(productSeriesId) else selected.add(productSeriesId)

        updateState { copy(checkedProductSeriesIds = selected) }
    }

    fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen(
                    seriesIds = getSelectedProductSeries()
                )
            )
        }
    }

    private fun getSelectedProductSeries(): List<String> =
        currentState().checkedProductSeriesIds.toList()

    private fun showLoadingView() {
        updateState {
            copy(
                screenState = ScreenState.LOADING,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                screenState = ScreenState.ERROR,
            )
        }
    }

    private companion object {
        private const val MAX_SELECTED_PRODUCT_SERIES = 5
    }
}