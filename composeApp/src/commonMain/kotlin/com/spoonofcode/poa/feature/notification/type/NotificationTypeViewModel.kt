package com.spoonofcode.poa.feature.notification.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.feature.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.product.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) : BaseViewModel<NotificationTypeViewState>(NotificationTypeViewState()) {

    fun navigateToAll() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationEditScreen())
        }
    }

    fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationEditScreen())
        }
    }

    fun navigateToPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(CustomerOverviewScreen(partnerId = getUserPartnersUseCase().first().id))
        }
    }

    fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }
}