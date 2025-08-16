package com.spoonofcode.poa.feature.product.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.product.GetAllProductSeriesUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProductSeriesOverviewViewModel(
    private val getAllProductSeriesUseCase: GetAllProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState>(ProductSeriesOverviewViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val productSeries = getAllProductSeriesUseCase()
                val selectedProductSeriesIds =
                    productSeries.map { product -> product.seriesId }.associateWith { false }
                updateState {
                    copy(
                        isLoadingView = false,
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

        currentProductSeries[productSeriesIdToUpdate as String] =
            currentProductSeries[productSeriesIdToUpdate]!!.not()

        updateState {
            copy(
                checkedProductSeriesIds = currentProductSeries.toMap()
            )
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
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}