package com.spoonofcode.poa.feature.notification.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.partner.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.partner.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.product.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) : BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(NotificationTypeViewState()) {

    override fun onAction(action: NotificationTypeViewAction) {
        when (action) {
            NotificationTypeViewAction.NavigateToAll -> navigateToAll()
            NotificationTypeViewAction.NavigateToPartnerCustomers -> navigateToPartnerCustomers()
            NotificationTypeViewAction.NavigateToPartnerProductSeries -> navigateToPartnerProductSeries()
            NotificationTypeViewAction.NavigateToAllPartnerCustomers -> navigateToAllPartnerCustomers()
        }
    }

    private fun navigateToAll() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationEditScreen())
        }
    }

    private fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationEditScreen())
        }
    }

    private fun navigateToPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(CustomerOverviewScreen(partnerId = getUserPartnersUseCase().first().id))
        }
    }

    private fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }
}