package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.appnavigation.ProductModule
import com.spoonofcode.feature.notification.presentation.edit.NotificationEditScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel() :
    BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(
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
                routeResolver.resolve(
                    PartnerModule.CustomerOverviewScreen(
                        partnerId = partnerId,
                    )
                )
            )
        }
    }

    private fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(routeResolver.resolve(ProductModule.ProductSeriesOverviewScreen))
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