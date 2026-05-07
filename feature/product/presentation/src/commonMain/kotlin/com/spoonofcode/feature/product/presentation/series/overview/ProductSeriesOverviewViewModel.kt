package com.spoonofcode.feature.product.presentation.series.overview

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.notification.domain.model.NotificationType
import com.spoonofcode.feature.partner.domain.usecase.GetPartnerProductSeriesUseCase
import kotlinx.coroutines.launch

internal class ProductSeriesOverviewViewModel(
    private val getPartnerProductSeriesUseCase: GetPartnerProductSeriesUseCase,
) : BaseViewModel<ProductSeriesOverviewViewState, ProductSeriesOverViewViewAction>(
    ProductSeriesOverviewViewState()
) {

    override fun onAction(action: ProductSeriesOverViewViewAction) {
        when (action) {
            is ProductSeriesOverViewViewAction.CheckedProductSeries -> checkedProductSeries(action.name)
            ProductSeriesOverViewViewAction.InitView -> initView()
            ProductSeriesOverViewViewAction.NavigateToNotification -> navigateToNotification()
        }
    }

    private fun initView() {
        showLoadingView()
        viewModelScope.launch {
            getPartnerProductSeriesUseCase()
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

    private fun checkedProductSeries(name: String) {
        val selected = currentContentViewState().checkedProductSeriesNames.toMutableSet()
        val wasAdded = selected.contains(name)

        if (!wasAdded && selected.size >= MAX_SELECTED_PRODUCT_SERIES) {
            showSnackbar(
                SnackbarEvent.Error("You can select a maximum of $MAX_SELECTED_PRODUCT_SERIES items.")
            )
            return
        }

        if (wasAdded) selected.remove(name) else selected.add(name)

        updateState {
            copy(
                checkedProductSeriesNames = selected,
            )
        }
    }

    private fun navigateToNotification() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(
                    NotificationModuleRoute.NotificationEdit(
                        notificationTypeName = NotificationType.serie.name,
                        selectedTopics = currentContentViewState().checkedProductSeriesNames,
                        selectedNames = currentContentViewState().checkedProductSeriesNames,
                    )
                )
            )
        }
    }

    private companion object {
        private const val MAX_SELECTED_PRODUCT_SERIES = 5
    }
}