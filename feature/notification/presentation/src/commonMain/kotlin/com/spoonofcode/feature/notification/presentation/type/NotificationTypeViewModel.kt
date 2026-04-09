package com.spoonofcode.feature.notification.presentation.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.ScreenState
import com.spoonofcode.feature.appnavigation.NotificationModule
import com.spoonofcode.feature.appnavigation.PartnerModule
import com.spoonofcode.feature.appnavigation.ProductModule
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
                routeResolver.resolve(NotificationModule.NotificationEditScreen())
            )
        }
    }

    private fun navigateToPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(
                routeResolver.resolve(PartnerModule.CustomerOverviewScreen)
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
                routeResolver.resolve(NotificationModule.NotificationEditScreen())
            )
        }
    }
}