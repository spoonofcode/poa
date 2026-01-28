package com.spoonofcode.poa.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.poa.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel() : BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(
    NotificationTypeViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: NotificationTypeViewAction) {
        when (action) {
            NotificationTypeViewAction.NavigateToAllCustomers -> navigateToAllCustomers()
            NotificationTypeViewAction.NavigateToPartnerCustomers -> navigateToPartnerCustomers()
            NotificationTypeViewAction.NavigateToPartnerProductSeries -> navigateToPartnerProductSeries()
            NotificationTypeViewAction.NavigateToAllPartnerCustomers -> navigateToAllPartnerCustomers()
        }
    }

    private fun navigateToAllCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen()
            )
        }
    }

    private fun navigateToPartnerCustomers() {
        // TODO #99 Adopt poa backend - read partner id from profile backend
        val partnerId = "4KJzqfBSQ1WbwysYa0j9"
        viewModelScope.launch {
            viewModelNavigator.push(
                CustomerOverviewScreen(
                    partnerId = partnerId
                )
            )
        }
    }

    private fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }

    private fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                NotificationEditScreen()
            )
        }
    }
}