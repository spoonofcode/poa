package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import com.spoonofcode.feature.partner.presentation.customer.overview.CustomerOverviewScreen
import com.spoonofcode.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel() : BaseViewModel<com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewState, com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction>(
    _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewState(),
    initialScreenState = ScreenState.CONTENT,
) {

    override fun onAction(action: com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction) {
        when (action) {
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllCustomers -> navigateToAllCustomers()
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerCustomers -> navigateToPartnerCustomers()
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToPartnerProductSeries -> navigateToPartnerProductSeries()
            _root_ide_package_.com.spoonofcode.feature.notification.presentation.type.NotificationTypeViewAction.NavigateToAllPartnerCustomers -> navigateToAllPartnerCustomers()
        }
    }

    private fun navigateToAllCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen()
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
                _root_ide_package_.com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen()
            )
        }
    }
}