package com.spoonofcode.feature.product.presentation.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.partner.domain.model.PartnerNotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerProductSeriesUseCase
import kotlinx.coroutines.launch

internal class ProductSeriesOverviewViewModel(
    private val getPartnerProductSeriesUseCase: GetPartnerProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState, ProductSeriesOverViewViewAction, Nothing>() {
    override val startAction = {
        initView()
    }

    override fun onAction(action: ProductSeriesOverViewViewAction) {
        when (action) {
            is ProductSeriesOverViewViewAction.CheckedProductSeries -> checkedProductSeries(action.name)
            ProductSeriesOverViewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerProductSeriesUseCase()
                .onSuccess {
                    setContentState(
                        ProductSeriesOverviewViewState(
                            productSeries = it,
                        )
                    )
                    showContentView()
                }
                .onFailure {
                    showErrorView()
                }
        }
    }

    private fun checkedProductSeries(name: String) {
        val selected = currentContentState().checkedProductSeriesNames.toMutableSet()
        val wasAdded = selected.contains(name)

        if (!wasAdded && selected.size >= MAX_SELECTED_PRODUCT_SERIES) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_PRODUCT_SERIES items.")
            )
            return
        }

        if (wasAdded) selected.remove(name) else selected.add(name)

        updateContentState {
            copy(
                checkedProductSeriesNames = selected,
            )
        }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                PartnerModuleRoute.PartnerNotificationEdit(
                    selectedTopics = currentContentState().checkedProductSeriesNames.map {
                        PartnerNotificationType.serie.createTopic(it)
                    }.toSet(),
                    selectedNames = currentContentState().checkedProductSeriesNames,
                )
            )
        }
    }

    private companion object {
        private const val MAX_SELECTED_PRODUCT_SERIES = 5
    }
}
