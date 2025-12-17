package com.spoonofcode.poa.feature.notification.type

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.domain.user.partner.GetUserPartnersUseCase
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.feature.notification.edit.NotificationEditScreen
import com.spoonofcode.poa.feature.partner.customer.overview.CustomerOverviewScreen
import com.spoonofcode.poa.feature.product.presentation.series.overview.ProductSeriesOverviewScreen
import kotlinx.coroutines.launch

internal class NotificationTypeViewModel(
    private val getUserPartnersUseCase: GetUserPartnersUseCase,
) : BaseViewModel<NotificationTypeViewState, NotificationTypeViewAction>(NotificationTypeViewState()) {

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
            viewModelNavigator.push(NotificationEditScreen())
        }
    }

    private fun navigateToPartnerCustomers() {
        viewModelScope.launch {
            getUserPartnersUseCase()
                .onSuccess {
                    val partnerId = it.first().id
                    viewModelNavigator.push(CustomerOverviewScreen(partnerId = partnerId))
                }
                .onFailure {
                    showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
                }
        }
    }

    private fun navigateToPartnerProductSeries() {
        viewModelScope.launch {
            viewModelNavigator.push(ProductSeriesOverviewScreen())
        }
    }

    private fun navigateToAllPartnerCustomers() {
        viewModelScope.launch {
            viewModelNavigator.push(NotificationEditScreen())
        }
    }
}