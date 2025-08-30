package com.spoonofcode.poa.feature.product.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetAllProductSeriesUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.ScreenState
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductSeriesOverviewViewModel(
    private val getAllProductSeriesUseCase: GetAllProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState>(ProductSeriesOverviewViewState()) {

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val productSeries = getAllProductSeriesUseCase()
                val selectedProductSeriesIds =
                    productSeries.map { product -> product.seriesId }.associateWith { false }
                updateState {
                    copy(
                        screenState = ScreenState.CONTENT,
                        productSeries = productSeries,
                        checkedProductSeriesIds = selectedProductSeriesIds
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
        val currentProductSeries = currentState().checkedProductSeriesIds.toMutableMap()
        val productSeriesIdToUpdate = currentProductSeries.keys.find { it == productSeriesId }
        val isAlreadyChecked = currentProductSeries[productSeriesIdToUpdate]

        if (isAlreadyChecked == true) {
            currentProductSeries[productSeriesIdToUpdate as String] =
                currentProductSeries[productSeriesIdToUpdate]!!.not()
            updateState {
                copy(
                    checkedProductSeriesIds = currentProductSeries.toMap()
                )
            }
        } else {
            if (currentProductSeries.filter { it.value }.size >= MAX_SELECTED_PRODUCT_SERIES) {
                showSnackbar(SnackbarEvent.Error(message = "You can select a maximum of 5 items."))
            } else {
                currentProductSeries[productSeriesIdToUpdate as String] =
                    currentProductSeries[productSeriesIdToUpdate]!!.not()
                updateState {
                    copy(
                        checkedProductSeriesIds = currentProductSeries.toMap()
                    )
                }
            }
        }
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
        currentState().checkedProductSeriesIds.filter { it.value }.keys.toList()

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